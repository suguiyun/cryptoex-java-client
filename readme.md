用户前端需要集成的接口：
创建底层用户：

```
POST /ui/users
```

```
{
  "canSignin": true,
  "canTrade": true,
  "canWithdraw": true
}
{
  "canSignin": true,
  "canTrade": true,
  "canWithdraw": true,
  "createdAt": 0,
  "id": 0,
  "level": 0,
  "type": "TRADER",
  "updatedAt": 0
}
```

获取用户余额：

```
GET /ui/users/{userId}/accounts
```

获取用户充值地址：

```
GET /ui/users/{userId}/deposit/{currency}/address
```

```
{
  "address": "0x1111111111111111111111",   ->  以太坊充币地址
  "bipIndex": 0,
  "createdAt": 0,
  "currency": "string",
  "id": 0,
  "userId": 0
}
```

获取用户充值记录：

```
GET /ui/users/{userId}/deposit/logs
```

用户添加删除提现地址：

```
POST /ui/users/{userId}/withdraw/addresses
```

获取用户提现地址：

```
GET /ui/users/{userId}/withdraw/addresses
GET /ui/users/{userId}/withdraw/addresses/{id}
```

用户删除提现地址：

```
POST /ui/users/{userId}/withdraw/addresses/{id}/delete
```

用户发起提现请求：

```
POST /ui/users/{userId}/withdraw/requests
```

用户取消提现请求：

```
POST /ui/users/{userId}/withdraw/requests/{id}/cancel
```

用户获取提现请求信息：

```
GET /ui/users/{userId}/withdraw/requests
```
