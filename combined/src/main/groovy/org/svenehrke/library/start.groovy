import org.opendolphin.core.client.comm.JavaFXUiThreadHandler
import org.opendolphin.core.comm.DefaultInMemoryConfig
import org.svenehrke.library.LibraryView

def config = new DefaultInMemoryConfig()
config.clientDolphin.clientConnector.uiThreadHandler = new JavaFXUiThreadHandler()
config.serverDolphin.registerDefaultActions()

LibraryView.show(config.clientDolphin)
