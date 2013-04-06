package org.svenehrke.library

class Constants {
	static class TYPE {
		public static final String BOOK = Constants.unique 'book'
	}
	static class ATT {
		public static final String DOMAIN_ID = 'domainId'
		public static final String TITLE      = 'title'
		public static final String AUTHOR     = 'author'

		static final List<String> ALL_ATTRIBUTES = [DOMAIN_ID, TITLE, AUTHOR]
	}
	static class PM_ID {
		public static final String SELECTED  = Constants.unique 'selected'
	}
	static class CMD {
		public static final String PULL  = Constants.unique 'pull'
	}

	static String unique(String s) { Constants.class.name + '.'+ s }
	static String pmId(String type, int index) { type + "-" + index}
}
