package org.svenehrke.library

import org.opendolphin.core.server.DTO
import org.opendolphin.core.server.Slot
import static org.svenehrke.library.Constants.ATT.AUTHOR
import static org.svenehrke.library.Constants.ATT.DOMAIN_ID
import static org.svenehrke.library.Constants.ATT.TITLE

class InMemoryBookService implements IBookService {
	@Override
	List<DTO> list() {
		List<DTO> books = new LinkedList<DTO>();
		books.addAll(
			new DTO(
				new Slot(DOMAIN_ID, 1),
				new Slot(TITLE, 'Refactoring'),
				new Slot(AUTHOR, 'Martin Fowler'),
			),
			new DTO(
				new Slot(DOMAIN_ID, 2),
				new Slot(TITLE, 'Design Patterns'),
				new Slot(AUTHOR, 'Gang of Four'),
			)
		);

		books
	}
}
