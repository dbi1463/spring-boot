package tw.exmaple.util;

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
	public static String getString(final JsonElement element, final String name) {
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
	public static String getString(final JsonElement element, final String name, final String defaultValue) {
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
	public static int getInteger(final JsonElement element, final String name) {
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
	public static int getInteger(final JsonElement element, final String name, final int defaultValue) {
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
	public static List<JsonElement> getElements(final JsonElement element, final String name) {
		return getElements(element, name, null);
	}

	/**
	 * Get the attribute specified by the given name as a list of JSON elements.
	 *
	 * @param element the JSON object
	 * @param name the attribute name
	 * @param defaultValue the default value if the specified attribute does not exist
	 * @return a list of JSON elements
	 */
	public static List<JsonElement> getElements(final JsonElement element, final String name, final List<JsonElement> defaultValues) {
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
	 * Parse the JSON string content as a JSON element.
	 *
	 * @param json the JSON string content
	 * @return the parsed JSON element
	 */
	public static JsonElement parse(final String json) {
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
	private static JsonArray getJsonArray(final JsonElement element, final String name) {
		JsonElement child = getElement(element, name);
		return child != null ? child.getAsJsonArray() : null;
	}

	/**
	 * Get the anonymous array as JSON array.
	 *
	 * @param element the JSON object
	 * @return the specified JSON array
	 */
	private static JsonArray getJsonArray(final JsonElement element) {
		return element.isJsonArray()? element.getAsJsonArray() : null;
	}

	/**
	 * Get the attribute specified by the given name as a JSON element.
	 *
	 * @param element the JSON object
	 * @param name the attribute name
	 * @return the specified JSON element
	 */
	public static JsonElement getElement(final JsonElement element, final String name) {
		return element != null && !(element instanceof JsonNull)? ((JsonObject)element).get(name) : null;
	}

	/**
	 * Check whether the element is a JSON primitive element.
	 *
	 * @param element the JSON element
	 * @return true if the element exists and is primitive element
	 */
	static boolean isPrimitiveElement(final JsonElement element) {
		return element != null && !element.isJsonNull() && element.isJsonPrimitive();
	}
}
