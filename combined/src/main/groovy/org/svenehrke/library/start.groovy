import org.opendolphin.core.client.comm.JavaFXUiThreadHandler
import org.opendolphin.core.comm.DefaultInMemoryConfig
import org.svenehrke.library.InMemoryBookService
import org.svenehrke.library.LibraryView
import org.svenehrke.library.PullRegistrarAction

def config = new DefaultInMemoryConfig()
config.clientDolphin.clientConnector.uiThreadHandler = new JavaFXUiThreadHandler()
config.serverDolphin.registerDefaultActions()

config.serverDolphin.register(new PullRegistrarAction(bookService: new InMemoryBookService()))

LibraryView.show(config.clientDolphin)
