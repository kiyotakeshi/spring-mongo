# Run Application

```
docker-compose up -d

docker-compose ps
```

## Mongo playground

```
docker-compose exec mongo bash

mongo

# DB の作成
use test

# DB の統計情報の確認
db.stats()

# test はコレクションを作成するまでは表示されない
show dbs

# collection の作成
db.createCollection("test-collection")

show collections

# collection の統計情報の確認
# collection 名が test なら、 `db.test.stats()`
db['test-collection'].stats()

# test も表示されるように
show dbs

# 一件登録
db['test-collection'].insertOne( { name: "test-user", age: 20 } )

# 複数登録
db['test-collection'].insertMany( [{ name: "test-user", age: 20 }, { name: "test-user2", age: 22 }, { name: "test-user3", age: 30 }] )

# 一意性は _id により担保されているため、 "test-user" 2件が別レコードとして登録されている
> db['test-collection'].find()
{ "_id" : ObjectId("6024c23aeb543841c3e65ebc"), "name" : "test-user", "age" : 20 }
{ "_id" : ObjectId("6024c2c7eb543841c3e65ebd"), "name" : "test-user", "age" : 20 }
{ "_id" : ObjectId("6024c2c7eb543841c3e65ebe"), "name" : "test-user2", "age" : 22 }
{ "_id" : ObjectId("6024c2c7eb543841c3e65ebf"), "name" : "test-user3", "age" : 30 }

# 配列データの登録
db['test-collection'].insertOne( { name: "test-user", age: 20, interests: ["spring", "java", "aws"] } )

# オブジェクトを含むデータの登録
db['test-collection'].insertOne({ name: "test-user", age: 30, child1: { name: "test-child1", age: 5}, child2: { name: "test-child2", age: 1} })

# name, interests フィールドだけを表示( _id は明示的に 0 を指定しないと表示される)
> db['test-collection'].find( {},{_id:0, name:1, interests:1} )
{ "name" : "test-user" }
{ "name" : "test-user" }
{ "name" : "test-user2" }
{ "name" : "test-user3" }
{ "name" : "test-user", "interests" : [ "spring", "java", "aws" ] }
{ "name" : "test-user" }

# name フィールドを検索条件にする
> db['test-collection'].find({ name: "test-user2" })
{ "_id" : ObjectId("6024c2c7eb543841c3e65ebe"), "name" : "test-user2", "age" : 22 }

# 複合条件で検索する
> db['test-collection'].find({ $and: [{name: "test-user"}, {age: {$gte: 25}}]}, {_id: 0, "child1.name": 1})
{ "child1" : { "name" : "test-child1" } }

# 削除
> db['test-collection'].find().size()
6

db['test-collection'].deleteOne({name:"test-user2"})

> db['test-collection'].find().size()
5

# コレクションの削除
db['test-collection'].drop()

db.dropDatabase()
```

