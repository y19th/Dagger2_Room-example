package y19th.example.dagger_roomexample.extension

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

suspend fun withMain(lambda:suspend CoroutineScope.() -> Unit) {
    lambda.invoke(CoroutineScope(Dispatchers.Main))
}
suspend fun withDefault(lambda: suspend CoroutineScope.() -> Unit) {
    lambda.invoke(CoroutineScope(Dispatchers.Default))
}