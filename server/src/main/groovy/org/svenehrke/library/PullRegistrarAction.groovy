package org.svenehrke.library
import org.opendolphin.core.server.DTO
import org.opendolphin.core.server.action.DolphinServerAction
import org.opendolphin.core.server.comm.ActionRegistry

import static org.svenehrke.library.Constants.CMD.PULL
import static org.svenehrke.library.Constants.TYPE.BOOK
import static org.svenehrke.library.Constants.pmId

class PullRegistrarAction extends DolphinServerAction {

	IBookService bookService

	void registerIn(ActionRegistry registry) {

		serverDolphin.action PULL, { cmd, response ->
			List<DTO> books = bookService.list()

			books.eachWithIndex { dto, index ->
				presentationModel pmId(BOOK, index), BOOK, dto
			}
		}

	}
}
