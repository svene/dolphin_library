package org.svenehrke.library
import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.opendolphin.core.client.ClientDolphin
import org.opendolphin.core.client.ClientPresentationModel

import static groovyx.javafx.GroovyFX.start
import static org.opendolphin.binding.JavaFxUtil.value
import static org.svenehrke.library.Constants.CMD.PULL
import static org.svenehrke.library.Constants.PM_ID.SELECTED

@SuppressWarnings("GroovyAssignabilityCheck")
class LibraryView {
	static show(ClientDolphin clientDolphin) {

		ObservableList<ClientPresentationModel> observableBooks = FXCollections.observableArrayList()
		def selectedBook = clientDolphin.presentationModel(SELECTED, bookId: null )

		def editor = new BookEditor(bookPM: selectedBook, clientDolphin: clientDolphin)

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
							pane id:'bookDetails', { editor.cachedOrNewView(sgb) }
						}
					}
				}
			}

			books.items = observableBooks

			books.selectionModel.selectedItemProperty().addListener( { o, oldVal, selectedPM ->
				if (null == selectedPM) return // happens on deselect
				selectedBook.bookId.value = selectedPM.id
			} as ChangeListener)

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
