package com.impromptu.gravatar;

import java.io.*;
import java.security.*;

public class Gravatar {

  public static String hex(byte[] array) {
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
      sb.append(Integer.toHexString((array[i]
          & 0xFF) | 0x100).substring(1,3));       
      }

      return sb.toString();
  }

  public static String md5Hex (String message) {
      try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      return hex (md.digest(message.getBytes("CP1252")));
      } catch (NoSuchAlgorithmException e) {
      } catch (UnsupportedEncodingException e) {
      }
      return null;
  }
}


//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.commons.io.IOUtils;
//import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang.Validate;
//
///**
// * A gravatar is a dynamic image resource that is requested from the
// * gravatar.com server. This class calculates the gravatar url and fetches
// * gravatar images. See http://en.gravatar.com/site/implement/url .
// * 
// * This class is thread-safe, Gravatar objects can be shared.
// * 
// * Usage example:
// * 
// * <code>
// * Gravatar gravatar = new Gravatar();
// * gravatar.setSize(50);
// * gravatar.setRating(GravatarRating.GENERAL_AUDIENCES);
// * gravatar.setDefaultImage(GravatarDefaultImage.IDENTICON);
// * String url = gravatar.getUrl("iHaveAn@email.com");
// * byte[] jpg = gravatar.download("info@ralfebert.de");
// * </code>
// */
//public final class Gravatar {
//
//	private final static int DEFAULT_SIZE = 80;
//	private final static String GRAVATAR_URL = "http://www.gravatar.com/avatar/";
//	private static final GravatarDefaultImage DEFAULT_DEFAULT_IMAGE = GravatarDefaultImage.HTTP_404;
//
//	private int size = DEFAULT_SIZE;
//	private GravatarDefaultImage defaultImage = DEFAULT_DEFAULT_IMAGE;
//
//	/**
//	 * Specify a gravatar size between 1 and 512 pixels. If you omit this, a
//	 * default size of 80 pixels is used.
//	 */
//	public void setSize(int sizeInPixels) {
//		Validate.isTrue(sizeInPixels >= 1 && sizeInPixels <= 512,
//				"sizeInPixels needs to be between 1 and 512");
//		this.size = sizeInPixels;
//	}
//
//	/**
//	 * Specify the default image to be produced if no gravatar image was found.
//	 */
//	public void setDefaultImage(GravatarDefaultImage defaultImage) {
//		Validate.notNull(defaultImage, "defaultImage");
//		this.defaultImage = defaultImage;
//	}
//
//	/**
//	 * Returns the Gravatar URL for the given email address.
//	 */
//	public String getUrl(String email) {
//		Validate.notNull(email, "email");
//
//		// hexadecimal MD5 hash of the requested user's lowercased email address
//		// with all whitespace trimmed
//		String emailHash = DigestUtils.md5Hex(email.toLowerCase().trim());
//		String params = formatUrlParameters();
//		return GRAVATAR_URL + emailHash + ".jpg" + params;
//	}
//
//	/**
//	 * Downloads the gravatar for the given URL using Java {@link URL} and
//	 * returns a byte array containing the gravatar jpg, returns null if no
//	 * gravatar was found.
//	 */
//	public byte[] download(String email) throws GravatarDownloadException {
//		InputStream stream = null;
//		try {
//			URL url = new URL(getUrl(email));
//			stream = url.openStream();
//			return IOUtils.toByteArray(stream);
//		} catch (FileNotFoundException e) {
//			return null;
//		} catch (Exception e) {
//			throw new GravatarDownloadException(e);
//		} finally {
//			IOUtils.closeQuietly(stream);
//		}
//	}
//
//	private String formatUrlParameters() {
//		List<String> params = new ArrayList<String>();
//
//		if (size != DEFAULT_SIZE)
//			params.add("s=" + size);
//		if (defaultImage != GravatarDefaultImage.GRAVATAR_ICON)
//			params.add("d=" + defaultImage.getCode());
//
//		if (params.isEmpty())
//			return "";
//		else
//			return "?" + StringUtils.join(params.iterator(), "&");
//	}
//
//}