package org.svenehrke.library

class Constants {
	static class PM_ID {
		public static final String SELECTED  = Constants.unique 'selected'
		public static final String PULL  = Constants.unique 'pull'
	}

	static String unique(String s) { Constants.class.name + '.'+ s }
}
