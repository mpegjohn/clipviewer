package com.vidlib.clipviewer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.UrlPathHelper;

@Controller
@RequestMapping(value = "/images")
public class FileAccess {
	@Autowired
	private WebApplicationContext context;
	
	/**
	 * 
	 */
	@RequestMapping(method = RequestMethod.GET)
	public void getFileFromStore(HttpServletRequest request, HttpServletResponse response) {
		UrlPathHelper helper = new UrlPathHelper();
		String pathInfo = helper.getServletPath(request);

		String filename = pathInfo.replaceFirst("/images/", "");

		File file = new File("/nas/newArchive", filename);

		response.setContentType(context.getServletContext().getMimeType(
				file.getName()));
		response.setContentLength((int) file.length());
		response.setHeader("Content-Disposition",
				"inline; filename=\"" + file.getName() + "\"");

		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			input = new BufferedInputStream(new FileInputStream(file));
			output = new BufferedOutputStream(response.getOutputStream());

			byte[] buffer = new byte[8192];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} catch (Exception ex) {

		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException ignore) {
				}
			if (input != null)
				try {
					input.close();
				} catch (IOException ignore) {
				}
		}

	}
}
