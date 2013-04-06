package org.svenehrke.library
import javafx.beans.value.ChangeListener
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import org.opendolphin.core.client.ClientDolphin
import org.opendolphin.core.client.ClientPresentationModel

import static groovyx.javafx.GroovyFX.start
import static org.opendolphin.binding.JFXBinder.bind
import static org.opendolphin.binding.JavaFxUtil.value
import static org.svenehrke.library.Constants.ATT.AUTHOR
import static org.svenehrke.library.Constants.ATT.TITLE
import static org.svenehrke.library.Constants.CMD.PULL
import static org.svenehrke.library.Constants.PM_ID.SELECTED

@SuppressWarnings("GroovyAssignabilityCheck")
class LibraryView {
	static show(ClientDolphin clientDolphin) {

		ObservableList<ClientPresentationModel> observableBooks = FXCollections.observableArrayList()
		def selectedBook = clientDolphin.presentationModel(SELECTED, bookId: null, active: false, (AUTHOR): '-', (TITLE): '-' )

		def editor = new BookEditor(selectedBook: selectedBook, clientDolphin: clientDolphin)

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
							pane id:'bookDetails'
						}
					}
				}
			}

			javafx.scene.Node editorNode = editor.cachedOrNewView(sgb)
			sgb.bookDetails.children << editorNode

			bind 'active' of selectedBook to 'visible' of bookDetails
			bind 'active' of selectedBook to 'visible' of (welcome, { !it} )

			books.items = observableBooks

			books.selectionModel.selectedItemProperty().addListener( { o, oldVal, selectedPM ->
				boolean gotDeselected = !selectedPM
				selectedBook.bookId.value = gotDeselected ? null : selectedPM.id
				selectedBook.active.value = !gotDeselected
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
