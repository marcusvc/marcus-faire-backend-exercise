package com.faire.marcus.exercise.selector;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.faire.marcus.exercise.model.Option;
import com.faire.marcus.exercise.model.Product;
import com.faire.marcus.exercise.model.Products;

@RunWith(PowerMockRunner.class)
//@PrepareForTest(NegativeQuantitiesSelector.class) //static mock
public class NegativeQuantitiesSelectorTest {
	
	@InjectMocks
	private NegativeQuantitiesSelector negativeQuantitiesSelector;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSelectEmptyNotNull() {
		Products products = new Products();
		products.setLimit(1);
		products.setPage(50);
		List<Product> productList = new ArrayList<>();
		productList.add(new Product());
		productList.get(0).setActive(true);
		productList.get(0).setBrandId("brandId");
		productList.get(0).setCreatedAt("20190322T164800.000Z");
		productList.get(0).setId("id");
		productList.get(0).setName("name");
		productList.get(0).setRetailPriceCents("500");
		productList.get(0).setShortDescription("shortDescription");
		productList.get(0).setUnitMultiplier(5);
		productList.get(0).setUpdatedAt("20190322T165000.000Z");
		productList.get(0).setWholesalePriceCents("1000");
		products.setProducts(productList);
		List<Option> options = new ArrayList<>();
		options.add(new Option());
		options.get(0).setActive(true);
		options.get(0).setAvailableQuantity(10);
		options.get(0).setBackorderedUntil("20190325T164800.000Z");
		options.get(0).setCreatedAt("20190320T164800.000Z");
		options.get(0).setId("id");
		options.get(0).setName("name");
		options.get(0).setProductId("productId");
		options.get(0).setSku("sku");
		options.get(0).setUpdatedAt("20190322T164800.000Z");
		productList.get(0).setOptions(options);
		List<Option> selecteds = negativeQuantitiesSelector.select(products);
		assertNotNull(selecteds);
		assertEquals(0, selecteds.size());
	}
	
	@Test
	public void testSelectNotEmpty() {
		Products products = new Products();
		products.setLimit(1);
		products.setPage(50);
		List<Product> productList = new ArrayList<>();
		productList.add(new Product());
		productList.get(0).setActive(true);
		productList.get(0).setBrandId("brandId");
		productList.get(0).setCreatedAt("20190322T164800.000Z");
		productList.get(0).setId("id");
		productList.get(0).setName("name");
		productList.get(0).setRetailPriceCents("500");
		productList.get(0).setShortDescription("shortDescription");
		productList.get(0).setUnitMultiplier(5);
		productList.get(0).setUpdatedAt("20190322T165000.000Z");
		productList.get(0).setWholesalePriceCents("1000");
		products.setProducts(productList);
		List<Option> options = new ArrayList<>();
		options.add(new Option());
		options.get(0).setActive(true);
		options.get(0).setAvailableQuantity(-10);
		options.get(0).setBackorderedUntil("20190325T164800.000Z");
		options.get(0).setCreatedAt("20190320T164800.000Z");
		options.get(0).setId("id");
		options.get(0).setName("name");
		options.get(0).setProductId("productId");
		options.get(0).setSku("sku");
		options.get(0).setUpdatedAt("20190322T164800.000Z");
		productList.get(0).setOptions(options);
		List<Option> selecteds = negativeQuantitiesSelector.select(products);
		assertNotNull(selecteds);
		assertEquals(1, selecteds.size());
		
	}

}
