import org.hyperledger.indy.sdk.LibIndy
import org.hyperledger.indy.sdk.wallet.Wallet
import kotlinx.serialization.*
import kotlinx.serialization.json.*

fun main() {

    // Load libindy.dylib from LD_LIBRARY_PATH
    System.loadLibrary("indy")

    // Initialise libindy
    if (!LibIndy.isInitialized()) {
        LibIndy.init()
    }

    // Create a sample Indy wallet
    val walletName = "SampleWallet"
    val walletType = "default"

    var walletConfig = HashMap<String, String>()
    walletConfig.put("id", walletName)
    walletConfig.put("storage_type", walletType)

    var walletCredentials = HashMap<String, String>()
    walletCredentials.put("key", "SampleWalletKey")
    walletCredentials.put("key_derivation_method", "RAW")

    Wallet.createWallet(Json.encodeToString(walletConfig), Json.encodeToString(walletCredentials))
}