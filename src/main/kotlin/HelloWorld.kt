import org.hyperledger.indy.sdk.LibIndy
import org.hyperledger.indy.sdk.wallet.Wallet


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
    val walletConfig = "{ \"id\":\"$walletName\", \"storage_type\":\"$walletType\"}"
    val walletCredentials =
        "{\"key\":\"SampleWalletKey\", \"key_derivation_method\":\"RAW\"}"

    Wallet.createWallet(walletConfig, walletCredentials)
}