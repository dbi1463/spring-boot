package tw.exmaple.util;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JsonUtils {

	/**
	 * Get the attribute specified by the given name as a string value.
	 * 
	 * @param element the JSON object
	 * @param name the attribute name
	 * @return the string value of the attribute
	 */
	public static String getString(JsonElement element, String name) {
		return getString(element, name, null);
	}

	/**
	 * Get the attribute specified by the given name as a string value.
	 * 
	 * @param element the JSON object
	 * @param name the attribute name
	 * @param defaultValue the default value if the specified attribute does not exist
	 * @return the string value of the attribute
	 */
	public static String getString(JsonElement element, String name, String defaultValue) {
		JsonElement child = getElement(element, name);
		return isPrimitiveElement(child)? child.getAsString() : defaultValue;
	}

	/**
	 * Get the attribute specified by the given name as an integer value.
	 * 
	 * @param element the JSON object
	 * @param name the attribute name
	 * @return the integer value of the attribute; 0 if the specified attribute does not exist
	 */
	public static int getInteger(JsonElement element, String name) {
		return getInteger(element, name, 0);
	}

	/**
	 * Get the attribute specified by the given name as an integer value.
	 * 
	 * @param element the JSON object
	 * @param name the attribute name
	 * @param defaultValue the default value if the specified attribute does not exist
	 * @return the integer value of the attribute
	 */
	public static int getInteger(JsonElement element, String name, int defaultValue) {
		JsonElement child = getElement(element, name);
		return isPrimitiveElement(child)? child.getAsInt() : defaultValue;
	}

	/**
	 * Get the attribute specified by the given name as a list of JSON elements.
	 * 
	 * @param element the JSON object
	 * @param name the attribute name
	 * @return a list of JSON elements; a null list if the specified attribute does not exist
	 */
	public static List<JsonElement> getElements(JsonElement element, String name) {
		return getElements(element, name, null);
	}
	
	/**
	 * Get the anonymous array inside the element as a list of JSON elements.
	 * 
	 * @param element the JSON object
	 * @return a list of JSON elements; a null list if the specified attribute does not exist
	 */
	public static List<JsonElement> getElements(JsonElement element) {
		return getElements(element, null, null);
	}

	/**
	 * Get the attribute specified by the given name as a list of JSON elements.
	 * 
	 * @param element the JSON object
	 * @param name the attribute name
	 * @param defaultValue the default value if the specified attribute does not exist
	 * @return a list of JSON elements
	 */
	public static List<JsonElement> getElements(JsonElement element, String name, List<JsonElement> defaultValues) {
		JsonArray children = (name == null ? getJsonArray(element) : getJsonArray(element, name));
		List<JsonElement> elements = (children != null) ? new ArrayList<JsonElement>() : defaultValues;
		if (children != null) {
			for (JsonElement child : children) {
				elements.add(child);
			}
		}
		return elements;
	}

	/**
	 * Parse the content from the given input stream as a JSON element.
	 * 
	 * @param stream the input stream
	 * @return the parsed JSON element
	 */
	public static JsonElement parse(InputStream stream) {
		JsonParser parser = new JsonParser();
		Charset charset = Charset.forName("UTF-8");
		return parser.parse(new InputStreamReader(stream, charset));
	}

	/**
	 * Parse the JSON string content as a JSON element.
	 * 
	 * @param json the JSON string content
	 * @return the parsed JSON element
	 */
	public static JsonElement parse(String json) {
		JsonParser parser = new JsonParser();
		return (json != null)? parser.parse(json) : null;
	}

	/**
	 * Get the attribute specified by the given name as JSON array.
	 * 
	 * @param element the JSON object
	 * @param name the attribute name
	 * @return the specified JSON array
	 */
	private static JsonArray getJsonArray(JsonElement element, String name) {
		JsonElement child = getElement(element, name);
		return child != null ? child.getAsJsonArray() : null;
	}
	
	/**
	 * Get the anonymous array as JSON array.
	 * 
	 * @param element the JSON object
	 * @return the specified JSON array
	 */
	private static JsonArray getJsonArray(JsonElement element) {
		return element.isJsonArray()? element.getAsJsonArray() : null;
	}

	/**
	 * Get the attribute specified by the given name as a JSON element.
	 * 
	 * @param element the JSON object
	 * @param name the attribute name
	 * @return the specified JSON element
	 */
	public static JsonElement getElement(JsonElement element, String name) {
		return element != null && !(element instanceof JsonNull)? ((JsonObject)element).get(name) : null;
	}

	/**
	 * Check whether the element is a JSON primitive element.
	 * 
	 * @param element the JSON element
	 * @return true if the element exists and is primitive element
	 */
	static boolean isPrimitiveElement(JsonElement element) {
		return element != null && !element.isJsonNull() && element.isJsonPrimitive();
	}
}
