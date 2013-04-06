package org.svenehrke.library
import groovyx.javafx.SceneGraphBuilder
import org.opendolphin.core.client.ClientDolphin
import org.opendolphin.core.client.ClientPresentationModel

@SuppressWarnings("GroovyAssignabilityCheck")
class BookEditor {

	ClientPresentationModel bookPM
	private javafx.scene.Node view
	private ClientDolphin clientDolphin

	javafx.scene.Node cachedOrNewView(SceneGraphBuilder sgb) {
		if (! view) {
			view = newView sgb
		}
		return view
	}

	private javafx.scene.Node newView(SceneGraphBuilder sgb) {
		sgb.with {
			javafx.scene.Node result = gridPane hgap:10, vgap:12, padding: 20, {
				columnConstraints     minWidth: 80, halignment: "right"
				label       "Book",    row: 0, column: 0
			}

			return result
		}

	}
}
