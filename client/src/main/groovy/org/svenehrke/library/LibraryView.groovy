package org.svenehrke.library

import javafx.collections.ObservableList
import javafx.collections.FXCollections
import org.opendolphin.core.client.ClientDolphin
import org.opendolphin.core.client.ClientPresentationModel
import static org.svenehrke.library.Constants.PM_ID.SELECTED
import static org.svenehrke.library.Constants.CMD.PULL

import static groovyx.javafx.GroovyFX.start
import static org.opendolphin.binding.JavaFxUtil.value

@SuppressWarnings("GroovyAssignabilityCheck")
class LibraryView {
	static show(ClientDolphin clientDolphin) {

		ObservableList<ClientPresentationModel> observableBooks = FXCollections.observableArrayList()
		def selectedBook = clientDolphin.presentationModel(SELECTED, portfolioId: null )

		start { app ->
			def sgb = delegate
			stage title:'Dolphin Library', {
				scene width: 1000, height: 600, stylesheets: 'main.css', {
					splitPane  {
						dividerPosition(index: 0, position: 0.4)
						vbox alignment: 'top_center', {
							label "Books", id: 'bookListTitle'
							tableView id: 'books', selectionMode: 'single', vgrow: 'always', {
								value 'title',  tableColumn('Title', prefWidth: sgb.bind(books.width() / 2) )
								value 'author', tableColumn('Author',     prefWidth: sgb.bind(books.width() / 2) )
							}
						}
						stackPane {
							text "Please select a Book", id: 'welcome'
							tabPane id:'bookTabs'
						}
					}
				}
			}

			books.items = observableBooks

			clientDolphin.send PULL, { pms ->
				for (pm in pms) {
					observableBooks << pm
				}
				fadeTransition(1.s, node: books, to: 1).playFromStart()
			}


			primaryStage.show()
		}
	}
}
