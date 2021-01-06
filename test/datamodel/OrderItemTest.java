package datamodel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.FixMethodOrder;


/**
 * 
 * JUnit4 test code for OrderItem class.
 * 
 * Use of assertions, see:
 *   https://junit.org/junit4/javadoc/latest/org/junit/Assert.html
 * 
 * @author sgra64
 */
@FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class OrderItemTest {


	/*
	 * Test fixtures - objects needed to perform the tests
	 */
	private final Article aToaster = new Article( "SKU-868682", "Toaster", 2499, 1200 );

	private final OrderItem oiToaster = new OrderItem( aToaster.getDescription(), aToaster, 12 );


	/*
	 * Test constructor, regular case.
	 */
	@Test
	public void test001_RegularConstructor() {
		OrderItem oi = new OrderItem( aToaster.getDescription(), aToaster, 12 );
		assertEquals( oi.getDescribtion(), aToaster.getDescription() );
		assertSame( oi.getArticle(), aToaster );
		assertEquals( oi.getUnitOrdered(), 12 );
	}

	/*
	 * Test constructor, special case with empty String and 0 - arguments.
	 */
	@Test
	public void test002_EmptyArgumentConstructor() {
		OrderItem oi = new OrderItem( "", aToaster, 0 );
		assertEquals( oi.getDescribtion(), "" );
		assertSame( oi.getArticle(), aToaster );
		assertEquals( oi.getUnitOrdered(), 0 );
	}

	/*
	 * Test constructor, Test special case with null and < 0 - arguments.
	 */
	@Test
	public void test003_NullArgumentConstructor() {
		OrderItem oi = new OrderItem( null, null, -1 );
		assertEquals( oi.getDescribtion(), "" );			// assert null for description
		assertNull( oi.getArticle() );						// assert null for article
		assertEquals( oi.getUnitOrdered(), 0 );			// 0 returned for unitsOrdered < 0
	}

	@Test
	public void test010_SetDescription() {
		final String description = "Super Toaster Model XRC-2484698";
		oiToaster.setDescribtion( description );			// regular case
		assertEquals( oiToaster.getDescribtion(), description );

		oiToaster.setDescribtion( "" );						// corner case: ""
		assertEquals( oiToaster.getDescribtion(), "" );

		oiToaster.setDescribtion( null );					// irregular case: null
		assertEquals( oiToaster.getDescribtion(), "" );		// "" returned for description: null
	}

	@Test
	public void test011_SetUnitsOrdered() {
		final int units = 100;
		oiToaster.setUnitOrdered( units );					// regular case
		assertEquals( oiToaster.getUnitOrdered(), units );

		oiToaster.setUnitOrdered( 0 );						// corner case: 0
		assertEquals( oiToaster.getUnitOrdered(), 0 );

		oiToaster.setUnitOrdered( Integer.MAX_VALUE );		// corner case: Long.MAX_VALUE
		assertEquals( oiToaster.getUnitOrdered(), Integer.MAX_VALUE );

		oiToaster.setUnitOrdered( -1 );					// irregular case: -1
		assertEquals( oiToaster.getUnitOrdered(), 0 );		// -> 0

		oiToaster.setUnitOrdered( Integer.MIN_VALUE );		// irregular case: Long.MIN_VALUE
		assertEquals( oiToaster.getUnitOrdered(), 0 );		// -> 0
	}

}
