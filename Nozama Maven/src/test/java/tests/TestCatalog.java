package tests;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import nozamaFiles.ItemCatalog;
import nozamaFiles.ItemSpecification;

import java.io.File;


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
