import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.hyperledger.indy.sdk.LibIndy
import org.hyperledger.indy.sdk.wallet.Wallet

/**
 * Initialise Indy SDK
 */
fun initLibIndy() {
    // Load libindy.dylib from LD_LIBRARY_PATH
    System.loadLibrary("indy")

    // Initialise libindy
    if (!LibIndy.isInitialized()) {
        LibIndy.init()
    }
}

/**
 * Create Indy wallet
 */
fun createWallet(walletName: String, walletKey: String) {
    val walletType = "default"

    val walletConfig = HashMap<String, String>()
    walletConfig["id"] = walletName
    walletConfig["storage_type"] = walletType

    val walletCredentials = HashMap<String, String>()
    walletCredentials["key"] = walletKey
    walletCredentials["key_derivation_method"] = "RAW"

    Wallet.createWallet(Json.encodeToString(walletConfig), Json.encodeToString(walletCredentials))
}