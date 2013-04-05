package org.svenehrke.library

import org.opendolphin.core.client.ClientDolphin
import static groovyx.javafx.GroovyFX.start

class LibraryView {
	static show(ClientDolphin clientDolphin) {

		start { app ->
			def sgb = delegate
			stage title:'Dolphin Library', {
				scene width: 1000, height: 600/*, stylesheets:"CrudDemo.css"*/, {
				}
			}

			primaryStage.show()
		}
	}
}
