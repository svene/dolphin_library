package org.svenehrke.library
import org.opendolphin.core.server.DTO
import org.opendolphin.core.server.Slot
import org.opendolphin.core.server.action.DolphinServerAction
import org.opendolphin.core.server.comm.ActionRegistry

import static org.svenehrke.library.Constants.ATT.AUTHOR
import static org.svenehrke.library.Constants.ATT.DOMAIN_ID
import static org.svenehrke.library.Constants.ATT.TITLE
import static org.svenehrke.library.Constants.CMD.PULL
import static org.svenehrke.library.Constants.TYPE.BOOK
import static org.svenehrke.library.Constants.pmId

class PullRegistrarAction extends DolphinServerAction {
	void registerIn(ActionRegistry registry) {

		serverDolphin.action PULL, { cmd, response ->
			List<DTO> books = new LinkedList<DTO>();
			books.add(new DTO(
				new Slot(DOMAIN_ID, 1),
				new Slot(TITLE, 'Refactoring'),
				new Slot(AUTHOR, 'Martin Fowler'),
			));
			books.add(new DTO(
				new Slot(DOMAIN_ID, 2),
				new Slot(TITLE, 'Design Patterns'),
				new Slot(AUTHOR, 'Gang of Four'),
			));

			books.eachWithIndex { dto, index ->
				presentationModel pmId(BOOK, index), BOOK, dto
			}
		}

	}
}
