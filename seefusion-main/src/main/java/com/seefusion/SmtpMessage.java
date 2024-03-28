/**
 * 
 */
package com.seefusion;

import java.util.TimeZone;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * @author Daryl
 *
 */
class SmtpMessage {

	String smtpDate;

	String smtpFrom;
	
	String smtpTo;

	String[] smtpToArray;

	String smtpSubject;
	
	String smtpBody;
	
	String contentType = "text/plain; charset=\"UTF-8\"";

	/**
	 * @return Returns the smtpBody.
	 */
	String getSmtpBody() {
		return this.smtpBody;
	}

	/**
	 * @param smtpBody The smtpBody to set.
	 */
	void setSmtpBody(String smtpBody) {
		this.smtpBody = smtpBody;
	}

	/**
	 * @return Returns the smtpFrom.
	 */
	String getSmtpFrom() {
		return this.smtpFrom;
	}

	/**
	 * @param smtpFrom The smtpFrom to set.
	 */
	void setSmtpFrom(String smtpFrom) {
		this.smtpFrom = formatEmailAddressForSmtp(smtpFrom);
	}

	static String formatEmailAddressForSmtp(String addr) {
		addr = addr.trim();
		// assume ok if addr contains '<'
		if(addr.indexOf('<') == -1) {
			if( addr.indexOf(' ') != -1 ) {
				// format Name <email@addr>
				int pos = addr.lastIndexOf(' ');
				addr = addr.substring(pos+1, addr.length());
			}
			// format <email@addr>
			addr = "<" + addr + ">";
		}
		return addr;
	}
	

	/**
	 * @return Returns the smtpSubject.
	 */
	String getSmtpSubject() {
		return this.smtpSubject;
	}

	/**
	 * @param smtpSubject The smtpSubject to set.
	 */
	void setSmtpSubject(String smtpSubject) {
		this.smtpSubject = smtpSubject;
	}

	/**
	 * @return Returns the smtpTo.
	 */
	String getSmtpTo() {
		return this.smtpTo;
	}

	/**
	 * @param smtpTo The smtpTo to set.
	 */
	void setSmtpTo(String smtpTo) {
		this.smtpTo = smtpTo;
		smtpToArray = smtpTo.split(",");
		for(int i = 0; i < smtpToArray.length; i++) {
			smtpToArray[i] = formatEmailAddressForSmtp(smtpToArray[i]);
		}
	}

	/**
	 * @return Returns the smtpToArray.
	 */
	String[] getSmtpToArray() {
		return this.smtpToArray;
	}

	/**
	 * @return the contentType
	 */
	String getContentType() {
		return this.contentType;
	}

	/**
	 * @param contentType the contentType to set
	 */
	void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * @return Returns the smtpDate.
	 */
	String getSmtpDate() {
		return this.smtpDate;
	}

	/**
	 * @param smtpDate The smtpDate to set.
	 */
	void setSmtpDate(String smtpDate) {
		TimeZone serverTimeZone = TimeZone.getDefault();
		ZoneId serverZoneId = serverTimeZone.toZoneId();
		ZonedDateTime currentTimeInServerZone = ZonedDateTime.now(serverZoneId);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
		this.smtpDate = currentTimeInServerZone.format(formatter);
	}
	
}
