package org.svenehrke.library
import org.opendolphin.core.server.DTO
import org.opendolphin.core.server.Slot

import static org.svenehrke.library.Constants.ATT.*

class InMemoryBookService implements IBookService {
	@Override
	List<DTO> list() {
		[
			[(DOMAIN_ID): 1, (TITLE): 'Refactoring', (AUTHOR): 'Martin Fowler'],
			[(DOMAIN_ID): 2, (TITLE): 'Design Patterns', (AUTHOR): 'Gang of Four'],
		].collect { new DTO(Slot.slots(it))  }
	}
}
