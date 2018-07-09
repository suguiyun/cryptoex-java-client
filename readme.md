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

用户添加提现地址：

```
POST /ui/users/{userId}/withdraw/addresses
```

```
{
    "address":"",
    "currency":"",
    "description":""
}
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

用户获取充值规则
```
GET /ui/deposit/{currency}/rules
```

获取用户信息
```
GET /ui/users/{userId}
```

修改用户信息
```
POST /ui/users/{userId}
```

获取提现规则
```
GET /ui/withdraw/rules
```

获取用户全部账户
```
GET /v1/user/accounts
```

获取系统所有币种
```
GET /v1/common/currencies
```

获取指定币种
```
GET /v1/common/currencies/{currency}
```

获取错误code
```
GET /v1/common/errorCodes
```

获取时间戳
```
GET /v1/common/timestamp
```

获取版本号
```
GET /v1/common/version
```


后台集成的接口


冻结账户
```
POST /manage/accounts/freeze
```

```
{
  "amount": 0,
  "currency": "string",
  "flowType": "TRADE_FREEZE",
  "userId": 0
}
```

资产转移
```
POST /manage/accounts/transfer
```

```
{
  "amount": 0,
  "currency": "string",
  "description": "string",
  "flowType": "TRADE_FREEZE",
  "fromUserId": 0,
  "toUserId": 0
}
```

资产解冻
```
POST /manage/accounts/unfreeze
```

```
{
  "amount": 0,
  "currency": "string",
  "flowType": "TRADE_FREEZE",
  "userId": 0
}
```

添加充值规则
```
POST /manage/deposits/{currency}/rules
```

```
{
  "rules": [
    {
      "amount": 0,
      "confirms": 0
    }
  ]
}
```

更新用户信息
```
POST /manage/users/enable
```

```
{
  "canSignin": true,
  "canTrade": true,
  "canWithdraw": true,
  "userId": 0
}
```

获取指定用户指定币种的账户信息
```
GET /manage/users/{userId}/accounts/{currency}
```

检查用户提现请求
```
POST /manage/withdraws/{withdrawRequestId}/check
```

```
{
  "errorCode": "string",
  "errorMessage": "string",
  "needReview": true,
  "success": true
}
```

review用户提现请求
```
POST /manage/withdraws/{withdrawRequestId}/review
```

```
{
  "approved": true,
  "errorCode": "string",
  "errorMessage": "string"
}
```

获取指定币种充值规则
```
GET /manage/{currency}/deposit/rules
```


添加提现规则
```
POST /manage/{currency}/withdraw/rules
```

```
{
  "feeRate": 0,
  "maximumFee": 0,
  "minimumAmount": 0,
  "minimumFee": 0,
  "withdrawDisabled": true
}
```





