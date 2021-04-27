package tests;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import nozamaFiles.Item;
import nozamaFiles.ItemCatalog;
import nozamaFiles.ItemSpecification;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBException;

public class TestCatalog {
	private ItemCatalog catalog = null;

	@BeforeEach
	void init() {
		catalog = new ItemCatalog(null);
	}

	@Test
	void testNullLoad() {
		assertThrows(NullPointerException.class, () -> {
			ItemCatalog.loadData(null);
		});
	}

	@Test
	void testValidLoad() {
		File file = new File("resources/testCatalog.csv");
		assertDoesNotThrow(() -> ItemCatalog.loadData(file));
	}
	
	@Test
	void testGetItemSpec() {
		File file = new File("resources/testCatalog.csv");
		assertDoesNotThrow(() -> ItemCatalog.loadData(file));
		ItemSpecification i = ItemCatalog.getItemSpecification("1");
		
		assertEquals(i.getName(), "item1");
	}
}
