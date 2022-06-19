import org.hyperledger.indy.sdk.IndyException
import org.json.JSONObject
import org.hyperledger.indy.sdk.LibIndy
import org.hyperledger.indy.sdk.wallet.Wallet
import java.util.concurrent.ExecutionException
import org.hyperledger.indy.sdk.wallet.WalletExistsException

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

    val walletConfig = JSONObject()
    walletConfig.put("id", walletName)
    walletConfig.put("storage_type", "default")

    val walletCredentials = JSONObject()
    walletCredentials.put("key", walletKey)
    walletCredentials.put("key_derivation_method", "ARGON2I_MOD")

    try {

        // Create wallet
        Wallet.createWallet(walletConfig.toString(), walletCredentials.toString()).get()

    } catch (e: ExecutionException) {

        // https://stackoverflow.com/questions/10437890/what-is-the-best-way-to-handle-an-executionexception
        try {

            // Throw actual exception
            throw e.cause!!

        } catch (wee: WalletExistsException) {

            // Wallet with this name already exists
            // Suppress exception (Do nothing for now)

        } catch (ie: IndyException) {

            // Throw any other exception
            throw ie

        }

    }

}