# 未起名字博客社区

# 主要功能
   
   1.实现登录功能（jwt）

# 主要技术

# 帮助文档
   ## mysql 表建立：
    mvn flyway:migrate
    
# 项目进度
    
   1.实现登录功能ing
   2. 注意要给一些业务加事务


# 后台API文档

## 用户

### 用户测试
    url：http://localhost:8080/api/userinfo
    请求类型：get
    结果：
        hahahhaha


### 用户注册
       url：http://localhost:8080/user/register
        请求类型：post
        数据：
            {
            	"userName":"波塞冬",
            	"password":"qwerty123",
            	"email":"2502644175@qq.com"
            }
        结果：
            {
                "code": 1,
                "msg": "OK",
                "result": null
            }


### 用户登录
    url：http://localhost:8080/user/login
    请求类型：post
    实例：
    {
        "userName":"拉拉啊",
        "password":"ashjgd132"
    }
    结果：
    {
        "code": 400,
        "msg": "密码错误！",
        "claims": null
    }




## 文章
### 发表文章
    url：http://localhost:8080/article/publish
    请求类型：post
    示例：
    {
        "title":"第2次test",
        "summary":"这是一个比较简短的一个不知道谢啥了。。",
        "classId":1,
        "content":"kjsdhfilsahdlkjfghseakj hhf 的机房卡合格方可就1313.a.wer251"
    }
    结果：
    {
        "code": 1,
        "msg": "保存成功！"
    }

### 文章更新
    url：http://localhost:8080/article/update/2
    请求类型：put
    解释：2位文章id
    实例：
    {
        "title":"第2次test",
        "summary":"2222222222。。",
        "classId":2,
        "content":"嘻嘻嘻的机房卡合格方可就1313.a.wer251"
    }
    结果：
    {
        "code": 1,
        "msg": "更新成功！"
    }

### 文章删除
    url：http://localhost:8080/article/delete/2
    请求类型：delete
    解释：2为文章id
    结果：
    {
        "code": 1,
        "msg": "删除成功！"
    }

### 根据用户id查找文章
    url：http://localhost:8080/article/find/list/123456789sdvdf/1/5
    解释：用户id：123456789sdvdf
           curPage：1
           pageSize：5
    请求类型：get
    结果：
    {
        "code": 1,
        "msg": "查找成功！",
        "result": [
            {
                "id": 1,
                "title": "第一次test",
                "userId": "123456789sdvdf",
                "createTime": "2019-10-06T09:14:22.000+0000",
                "updateTime": "2019-10-06T09:14:22.000+0000",
                "summary": "这是一个比较简短的一个不知道谢啥了。。",
                "pollCount": 0,
                "commentCount": 0,
                "readCount": 0,
                "classId": 1,
                "content": null,
                "articleDetail": null,
                "essence": false,
                "top": false
            },
            {
                "id": 3,
                "title": "lala 次test",
                "userId": "123456789sdvdf",
                "createTime": "2019-10-06T12:36:12.000+0000",
                "updateTime": "2019-10-06T12:36:12.000+0000",
                "summary": "hahahahha这是一个比较简短的一个不知道谢啥了。。",
                "pollCount": 0,
                "commentCount": 0,
                "readCount": 0,
                "classId": 2,
                "content": null,
                "articleDetail": {
                    "id": 2,
                    "articleId": 3,
                    "content": "kjsdhfilsahdlkjfghseakj hhf 的机房卡合格方可就1313.a.wer25skjdahgfjkhes s的空间划分卡死和1"
                },
                "essence": false,
                "top": false
            }
        ]
    }
    
### 查找所有文章

    url：http://localhost:8080/article/find/list/1/5
    解释：
        curPage：1
        pageSize：5
    类型:get
    结果：
    {
        "page": 1,
        "total": 2,
        "result": [
            {
                "id": 1,
                "title": "第一次test",
                "userId": "123456789sdvdf",
                "createTime": "2019-10-06T09:14:22.000+0000",
                "updateTime": "2019-10-06T09:14:22.000+0000",
                "summary": "这是一个比较简短的一个不知道谢啥了。。",
                "pollCount": 0,
                "commentCount": 0,
                "readCount": 0,
                "classId": 1,
                "content": null,
                "articleDetail": null,
                "essence": false,
                "top": false
            },
            {
                "id": 3,
                "title": "lala 次test",
                "userId": "123456789sdvdf",
                "createTime": "2019-10-06T12:36:12.000+0000",
                "updateTime": "2019-10-06T12:36:12.000+0000",
                "summary": "hahahahha这是一个比较简短的一个不知道谢啥了。。",
                "pollCount": 0,
                "commentCount": 0,
                "readCount": 0,
                "classId": 2,
                "content": null,
                "articleDetail": {
                    "id": 2,
                    "articleId": 3,
                    "content": "kjsdhfilsahdlkjfghseakj hhf 的机房卡合格方可就1313.a.wer25skjdahgfjkhes s的空间划分卡死和1"
                },
                "essence": false,
                "top": false
            }
        ]
    }
    


## 分类

### 添加分类
    url：http://localhost:8080/classfication/publish
    类型：post
    事例：
    {
        "name":"Java牛逼"
    }
    结果：
    {
        "code": 1,
        "msg": "保存分类成功！",
        "result": null
    }

### 查看所有分类
    url：http://localhost:8080/classfication/find/list
    类型：get
    结果：
    [
        {
            "id": 1,
            "name": "Java牛逼"
        }
    ]

### 更改分类名称
    url：http://localhost:8080/classfication/update
    类型：put
    事例：
    {
    	"id":2,
    	"name":"C++强势a"
    }
    结果：
    {
        "code": 1,
        "msg": "更新分类成功！",
        "result": null
    }

### 删除分类
    url：http://localhost:8080/classfication/delete/3
    类型：delete
    解释：3代表分类的id
    结果：
    {
        "code": 1,
        "msg": "删除分类成功！",
        "result": null
    }
    
## 评论管理

### 添加评论
     url：http://localhost:8080/classfication/delete/3
    类型：delete
    解释：3代表分类的id
    结果：
    {
        "code": 1,
        "msg": "删除分类成功！",
        "result": null
    }
   
   
### 查询某一个文章的评论（列表型）
    url：http://localhost:8080/comment/find/formatList/{articleId}
    类型：get
    示例：
        http://localhost:8080/comment/find/formatList/1
    结果：
    {
        "code": 200,
        "msg": "OK",
        "result": [
            {
                "commentId": 1,
                "fromUserName": "haha",
                "toUserName": "haha",
                "content": "楼主说的超棒的！",
                "articleId": 1
            },
            {
                "commentId": 2,
                "fromUserName": "haha",
                "toUserName": "haha",
                "content": "赞赞赞",
                "articleId": 3
            },
            {
                "commentId": 6,
                "fromUserName": "haha",
                "toUserName": "haha",
                "content": "评论aaa！",
                "articleId": 3
            },
            {
                "commentId": 9,
                "fromUserName": "haha",
                "toUserName": "haha",
                "content": "评论aaa！",
                "articleId": 3
            },
            {
                "commentId": 8,
                "fromUserName": "haha",
                "toUserName": "haha",
                "content": "评论aaa！",
                "articleId": 3
            },
            {
                "commentId": 5,
                "fromUserName": "haha",
                "toUserName": "haha",
                "content": "评论aaa！",
                "articleId": 3
            }
        ]
    }
   
### 查询所有文章的评论（树型）
    url：http://localhost:8080/comment/find/list/{curPage}/{pageSize}
    类型：get
    样例：http://localhost:8080/comment/find/list/1/3
    结果：
    {
        "code": 200,
        "msg": "OK",
        "result": [
            {
                "id": 1,
                "time": "2019-10-08T02:03:58.000+0000",
                "content": "楼主说的超棒的！",
                "articleId": 1,
                "fatherComment": null,
                "state": 2,
                "ipAddress": "0:0:0:0:0:0:0:1",
                "title": "第一次test",
                "commentUser": {
                    "createTime": "2019-09-27T04:21:22.000+0000",
                    "updateTime": "2019-09-27T04:21:22.000+0000",
                    "userId": "123456789sdvdf",
                    "userName": "haha",
                    "password": "123456",
                    "email": "2502644175@qq.com",
                    "avatar": null
                },
                "subComments": [
                    {
                        "id": 2,
                        "time": "2019-10-08T02:03:59.000+0000",
                        "content": "赞赞赞",
                        "articleId": 3,
                        "fatherComment": 1,
                        "state": 2,
                        "ipAddress": "0:0:0:0:0:0:0:1",
                        "title": "lala 次test",
                        "commentUser": {
                            "createTime": "2019-09-27T04:21:22.000+0000",
                            "updateTime": "2019-09-27T04:21:22.000+0000",
                            "userId": "123456789sdvdf",
                            "userName": "haha",
                            "password": "123456",
                            "email": "2502644175@qq.com",
                            "avatar": null
                        },
                        "subComments": [
                            {
                                "id": 6,
                                "time": "2019-10-08T13:47:08.000+0000",
                                "content": "评论aaa！",
                                "articleId": 3,
                                "fatherComment": 2,
                                "state": 2,
                                "ipAddress": "0:0:0:0:0:0:0:1",
                                "title": "lala 次test",
                                "commentUser": {
                                    "createTime": "2019-09-27T04:21:22.000+0000",
                                    "updateTime": "2019-09-27T04:21:22.000+0000",
                                    "userId": "123456789sdvdf",
                                    "userName": "haha",
                                    "password": "123456",
                                    "email": "2502644175@qq.com",
                                    "avatar": null
                                },
                                "subComments": [
                                    {
                                        "id": 9,
                                        "time": "2019-10-08T14:14:30.000+0000",
                                        "content": "评论aaa！",
                                        "articleId": 3,
                                        "fatherComment": 6,
                                        "state": 2,
                                        "ipAddress": "0:0:0:0:0:0:0:1",
                                        "title": "lala 次test",
                                        "commentUser": {
                                            "createTime": "2019-09-27T04:21:22.000+0000",
                                            "updateTime": "2019-09-27T04:21:22.000+0000",
                                            "userId": "123456789sdvdf",
                                            "userName": "haha",
                                            "password": "123456",
                                            "email": "2502644175@qq.com",
                                            "avatar": null
                                        },
                                        "subComments": []
                                    }
                                ]
                            },
                            {
                                "id": 8,
                                "time": "2019-10-08T13:47:35.000+0000",
                                "content": "评论aaa！",
                                "articleId": 3,
                                "fatherComment": 2,
                                "state": 2,
                                "ipAddress": "0:0:0:0:0:0:0:1",
                                "title": "lala 次test",
                                "commentUser": {
                                    "createTime": "2019-09-27T04:21:22.000+0000",
                                    "updateTime": "2019-09-27T04:21:22.000+0000",
                                    "userId": "123456789sdvdf",
                                    "userName": "haha",
                                    "password": "123456",
                                    "email": "2502644175@qq.com",
                                    "avatar": null
                                },
                                "subComments": []
                            }
                        ]
                    },
                    {
                        "id": 5,
                        "time": "2019-10-08T13:47:02.000+0000",
                        "content": "评论aaa！",
                        "articleId": 3,
                        "fatherComment": 1,
                        "state": 2,
                        "ipAddress": "0:0:0:0:0:0:0:1",
                        "title": "lala 次test",
                        "commentUser": {
                            "createTime": "2019-09-27T04:21:22.000+0000",
                            "updateTime": "2019-09-27T04:21:22.000+0000",
                            "userId": "123456789sdvdf",
                            "userName": "haha",
                            "password": "123456",
                            "email": "2502644175@qq.com",
                            "avatar": null
                        },
                        "subComments": []
                    }
                ]
            },
            {
                "id": 3,
                "time": "2019-10-08T13:22:50.000+0000",
                "content": "楼主说的超棒的abc！",
                "articleId": 3,
                "fatherComment": null,
                "state": 2,
                "ipAddress": "0:0:0:0:0:0:0:1",
                "title": "lala 次test",
                "commentUser": {
                    "createTime": "2019-09-27T04:21:22.000+0000",
                    "updateTime": "2019-09-27T04:21:22.000+0000",
                    "userId": "123456789sdvdf",
                    "userName": "haha",
                    "password": "123456",
                    "email": "2502644175@qq.com",
                    "avatar": null
                },
                "subComments": [
                    {
                        "id": 7,
                        "time": "2019-10-08T13:47:14.000+0000",
                        "content": "评论aaa！",
                        "articleId": 3,
                        "fatherComment": 3,
                        "state": 2,
                        "ipAddress": "0:0:0:0:0:0:0:1",
                        "title": "lala 次test",
                        "commentUser": {
                            "createTime": "2019-09-27T04:21:22.000+0000",
                            "updateTime": "2019-09-27T04:21:22.000+0000",
                            "userId": "123456789sdvdf",
                            "userName": "haha",
                            "password": "123456",
                            "email": "2502644175@qq.com",
                            "avatar": null
                        },
                        "subComments": []
                    }
                ]
            },
            {
                "id": 4,
                "time": "2019-10-08T13:46:34.000+0000",
                "content": "评论aaa！",
                "articleId": 3,
                "fatherComment": null,
                "state": 2,
                "ipAddress": "0:0:0:0:0:0:0:1",
                "title": "lala 次test",
                "commentUser": {
                    "createTime": "2019-09-27T04:21:22.000+0000",
                    "updateTime": "2019-09-27T04:21:22.000+0000",
                    "userId": "123456789sdvdf",
                    "userName": "haha",
                    "password": "123456",
                    "email": "2502644175@qq.com",
                    "avatar": null
                },
                "subComments": []
            }
        ]
    }


