Scatter Android
==============
ScatterAndroid simulates Scatter plugin for EOS blockchain in WebView, and allows client apps to:
- Retrieve account
- Sign transactions
- Sign arbitrary messages
 
## Installation
### Step 1: Add library
You can download a jar from GitHub's [releases page](https://github.com/mariabeyrak/ScatterAndroid/releases).

Or use Gradle:
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.mariabeyrak:ScatterAndroid:1.0.0'
}
``` 

### Step 2: Configure
Create ```ScatterClient```object, which implements actions, which you wish to handle:
```java
private ScatterClient scatterClient = new ScatterClient() {
        @Override
        public void getAccount(AccountReceived onAccountReceived) {
            /*some code*/
        }

         /*other actions*/
    };
``` 

Create ```Scatter``` object, where ```webView``` - is your WebView:
 ```java
 Scatter scatterImplementation = new Scatter(webView, scatterClient);
 ```
 
 Don`t forget to enable Javascript:
 ```java
 webView.getSettings().setJavaScriptEnabled(true);
 ```
 
 Clean all after your activity/fragment destroys:
 ```java
 scatterImplementation.removeInterface();
 ```

## Usage examples

### Send EOS account to Scatter
```java
        @Override
        public void getAccount(AccountReceived onAccountReceived) {
            onAccountReceived.onAccountReceivedSuccessCallback(accountName);
        }
```

### Sign arbitrary message
```java
        @Override
        public void completeMsgTransaction(MsgTransactionRequestParams params, MsgTransactionCompleted onMsgTransactionCompleted) {
            //generate signature
            Signature signature = signTransactionRaw(Hex.decode(params.getData()), privateKey).getSignature();
            onMsgTransactionCompleted.onMsgTransactionCompletedSuccessCallback(signature.toString());
        }
```
### Sign transaction
```java
        @Override
        public void completeTransaction(TransactionRequestParams transactionRequestParams, TransactionCompleted onTransactionCompleted) {
            //try to make transaction
            Transaction transaction = makeTransaction(transactionRequestParams, privateKey);
            String[] signatures = transaction.getSignatures();
            onTransactionCompleted.onTransactionCompletedSuccessCallback(signatures);
        }
```
### Send errors to Scatter 
```java
onTransactionCompleted.onTransactionCompletedErrorCallback(new ResponseCodeInfo(ResponseType.UNKNOWN_ERROR, ResponseCode.UNKNOWN_ERROR), "Error");
```
