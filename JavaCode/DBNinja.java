/*
	Bryson Gundry & Caroline Kistler
	4/17/2020
	Project 4 - Databases 
*/

package cpsc4620.antonspizza;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.sql.Timestamp;
import java.util.Date;

/*
This file is where most of your code changes will occur
You will write the code to retrieve information from the database, or save information to the database

The class has several hard coded static variables used for the connection, you will need to change those to your connection information

This class also has static string variables for pickup, delivery and dine-in. If your database stores the strings differently (i.e "pick-up" vs "pickup") changing these static variables will ensure that the comparison is checking for the right string in other places in the program. You will also need to use these strings if you store this as boolean fields or an integer.


*/

/**
 * A utility class to help add and retrieve information from the database
 */

public final class DBNinja {
    //enter your user name here
    private static String user = "PrjctPzr_bh2k";
    //enter your password here
    private static String password = "cpsc4620";
    //enter your database name here
    private static String database_name = "ProjectPizzeria_y5l7";
    //Do not change the port. 3306 is the default MySQL port
    private static String port = "3306";
    private static Connection conn;

    //Change these variables to however you record dine-in, pick-up and delivery, and sizes and crusts
    public final static String pickup = "pickup";
    public final static String delivery = "delivery";
    public final static String dine_in = "dine_in";

    public final static String size_s = "SMALL";
    public final static String size_m = "MEDIUM";
    public final static String size_l = "LARGE";
    public final static String size_xl = "X-LARGE";

    public final static String crust_thin = "THIN";
    public final static String crust_orig = "ORIGINAL";
    public final static String crust_pan = "PAN";
    public final static String crust_gf = "GLUTEN-FREE";



    /**
     * This function will handle the connection to the database
     * @return true if the connection was successfully made
     * @throws SQLException
     * @throws IOException
     */
    private static boolean connect_to_db() throws SQLException, IOException
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println ("Could not load the driver");

            System.out.println("Message     : " + e.getMessage());


            return false;
        }

        conn = DriverManager.getConnection("jdbc:mysql://mysql1.cs.clemson.edu:"+port+"/"+database_name, user, password);
        return true;
    }

    /**
     *
     * @param o order that needs to be saved to the database
     * @throws SQLException
     * @throws IOException
     * @requires o is not NULL. o's ID is -1, as it has not been assigned yet. The pizzas do not exist in the database
     *          yet, and the topping inventory will allow for these pizzas to be made
     * @ensures o will be assigned an id and added to the database, along with all of it's pizzas. Inventory levels
     *          will be updated appropriately
     */
    public static void addOrder(Order o) throws SQLException, IOException
    {
		/* add code to add the order to the DB. Remember to add the pizzas and discounts as well, which will involve multiple tables. Customer should already exist. Toppings will need to be added to the pizzas.

		It may be beneficial to define more functions to add an individual pizza to a database, add a topping to a pizza, etc.

		Note: the order ID will be -1 and will need to be replaced to be a fitting primary key.

		You will also need to add timestamps to your pizzas/orders in your database. Those timestamps are not stored in this program, but you can get the current time before inserting into the database

		Remember, when a new order comes in the ingredient levels for the topping need to be adjusted accordingly. Remember to check for "extra" of a topping here as well.

		You do not need to check to see if you have the topping in stock before adding to a pizza. You can just let it go negative.
		*/
	connect_to_db();

	String query = "INSERT INTO ORDERS(ID, TOTALCOSTBUS, TOTALPRICECUSTOMER)" + " VALUES(?,?,?);";
	PreparedStatement statement = conn.prepareStatement(query);

	int orderID = getLastID("ORDERS");
	statement.setInt(1, orderID + 1);
	statement.setDouble(2, 2.00);
	statement.setDouble(3, o.calcPrice());
	statement.executeUpdate();

	if(o.getType() == DBNinja.dine_in){ 
		DineInCustomer customer = (DineInCustomer) o.getCustomer();
		String queryDineIN = "INSERT INTO DINE_IN (ID, TABLENUM, SEATNUMS)" + " VALUES (?,?,?);";
		statement = conn.prepareStatement(queryDineIN);
		statement.setInt(1, orderID + 1);
		statement.setInt(2, customer.getTableNum());
		String seats = "";
		
		for(Integer i : customer.getSeats()){
			seats += i.toString() + " ";
		}
		statement.setString(3, seats);

		try {
		statement.executeUpdate();
		}
		catch (SQLException e) {
            		System.out.println("Error loading dineinOrder");

            		while (e != null) {
                		System.out.println("Message     : " + e.getMessage());
                		e = e.getNextException();
            		}
		conn.close();
		}
	}

        try {
	    manyPizzas(o.getPizzas(), orderID + 1);	
	}

	catch (SQLException e) {
            System.out.println("Error loading ORDERS");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            conn.close();
        }
	conn.close();
}

    

    /**
     *
     * @param c the new customer to add to the database
     * @throws SQLException
     * @throws IOException
     * @requires c is not null. C's ID is -1 and will need to be assigned
     * @ensures c is given an ID and added to the database
     */
    public static void addCustomer(ICustomer c) throws SQLException, IOException
    {
        connect_to_db();
		/*add code to add the customer to the DB.
		Note: the ID will be -1 and will need to be replaced to be a fitting primary key
		Note that the customer is an ICustomer data type, which means c could be a dine in, carryout or delivery customer
		*/
	String query = "INSERT INTO CUSTOMER(ID, TYPE, ORDERID)" + " VALUES (?,?,?);"; 
	PreparedStatement statement = conn.prepareStatement(query);
	int lastID = getLastID("CUSTOMER");
	String type = c.getType();
	int orderID = 0;
	statement.setInt(1, lastID + 1);
	statement.setString(2, type);
	statement.setInt(3, orderID);
	statement.executeUpdate();


	if(c.getType() == "pickup"){
		DineOutCustomer customer = (DineOutCustomer) c;
		lastID = getLastID("PICKUP");
		query = "INSERT INTO PICKUP (ID, FNAME, LNAME, PHONENUM)" + " VALUES (?,?,?,?);";
		statement = conn.prepareStatement(query);
		statement.setInt(1, lastID + 1);
		statement.setString(2, customer.getName());
		statement.setString(3, customer.getName());
		statement.setString(4, customer.getPhone());
		statement.executeUpdate();
		try {
			statement.executeUpdate();
		}
		catch (SQLException e) {
            		System.out.println("Error loading pickupCUSTOMER");
            		while (e != null) {
                		System.out.println("Message     : " + e.getMessage());
                		e = e.getNextException();
            		}
            		conn.close();
       		 }
	}

	else if(c.getType() == "delivery"){
		DeliveryCustomer customer = (DeliveryCustomer) c;
		lastID = getLastID("DELIVERY");
		query = "INSERT INTO DELIVERY (ID, FNAME, LNAME, PHONENUM, ADDRESS)" + " VALUES (?,?,?,?,?);";
		statement = conn.prepareStatement(query);
		statement.setInt(1, lastID + 1);
		statement.setString(2, customer.getName());
		statement.setString(3, customer.getName());
		statement.setString(4, customer.getPhone());
		statement.setString(5, customer.getAddress());
		try {
			statement.executeUpdate();
		}
		catch (SQLException e) {
            		System.out.println("Error loading deliveryCUSTOMER");
            		while (e != null) {
                		System.out.println("Message     : " + e.getMessage());
                		e = e.getNextException();
            		}
            		conn.close();
       		 }
	}
	conn.close();
}
    /**
     *
     * @param o the order to mark as complete in the database
     * @throws SQLException
     * @throws IOException
     * @requires the order exists in the database
     * @ensures the order will be marked as complete
     */
    public static void CompleteOrder(Order o) throws SQLException, IOException
    {
        connect_to_db();
		/*add code to mark an order as complete in the DB. You may have a boolean field for this, or maybe a completed time timestamp. However you have it, */
	String query = "UPDATE STATUS SET COMPLETED = TRUE WHERE ID = " + o.getID() + ";";
        conn.close();
    }

    /**
     *
     * @param t the topping whose inventory is being replenished
     * @param toAdd the amount of inventory of t to add
     * @throws SQLException
     * @throws IOException
     * @requires t exists in the database and toAdd > 0
     * @ensures t's inventory level is increased by toAdd
     */
    public static void AddToInventory(Topping t, double toAdd) throws SQLException, IOException
    {
        connect_to_db();
		/*add code to add toAdd to the inventory level of T. This is not adding a new topping, it is adding a certain amount of stock for a topping. This would be used to show that an order was made to replenish the restaurants supply of pepperoni, etc*/
	double total = t.getInv() + toAdd;
	String query = "UPDATE TOPPINGS SET INVENTORY = " + total + "WHERE ID = " + t.getID() + ";"; 
	PreparedStatement statement = conn.prepareStatement(query);
	try {
            statement.executeUpdate();
	}
        catch (SQLException e) {
            System.out.println("Error loading ADDINVENTORY");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            conn.close();
        }
	conn.close();
}

    public static void SubtractFromInventory(Topping t) throws SQLException, IOException
    {
        connect_to_db();

	double total = t.getInv() - 1;
	String query = "UPDATE TOPPINGS SET INVENTORY = " + total + "WHERE ID = " + t.getID() + ";"; 
	PreparedStatement statement = conn.prepareStatement(query);
	try {
            statement.executeUpdate();
	}
        catch (SQLException e) {
            System.out.println("Error loading SUBTRACTINVENTORY");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            conn.close();
        }
	conn.close();
}


    /*
        A function to get the list of toppings and their inventory levels. I have left this code "complete" as an example of how to use JDBC to get data from the database. This query will not work on your database if you have different field or table names, so it will need to be changed

        Also note, this is just getting the topping ids and then calling getTopping() to get the actual topping. You will need to complete this on your own

        You don't actually have to use and write the getTopping() function, but it can save some repeated code if the program were to expand, and it keeps the functions simpler, more elegant and easy to read. Breaking up the queries this way also keeps them simpler. I think it's a better way to do it, and many people in the industry would agree, but its a suggestion, not a requirement.
    */

    /**
     *
     * @return the List of all toppings in the database
     * @throws SQLException
     * @throws IOException
     * @ensures the returned list will include all toppings and accurate inventory levels
     */
    public static ArrayList<Topping> getInventory() throws SQLException, IOException
    {
        //start by connecting
        connect_to_db();
        ArrayList<Topping> ts = new ArrayList<Topping>();
        //create a string with out query, this one is an easy one
        String query = "Select ID From TOPPINGS;";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {
                int ID = rset.getInt(1);
                //Now I'm just passing my primary key to this function to get the topping itself individually
                ts.add(getTopping(ID));
            }
        }
        catch (SQLException e) {
            System.out.println("Error loading inventory");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return ts;
        }


        //end by closing the connection
        conn.close();
        return ts;
    }

    /**
     *
     * @return a list of all orders that are currently open in the kitchen
     * @throws SQLException
     * @throws IOException
     * @ensures all currently open orders will be included in the returned list.
     */
    public static ArrayList<Order> getCurrentOrders() throws SQLException, IOException
    {
        connect_to_db();

        ArrayList<Order> os = new ArrayList<Order>();

	String query = "Select ID From ORDERS;";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {
                int ID = rset.getInt(1);
                //Now I'm just passing my primary key to this function to get the topping itself individually
                //ts.add(getTopping(ID));
		os.add(getOrder(ID));
            }
        }
        catch (SQLException e) {
            System.out.println("Error loading current orders");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return os;
        }


        //end by closing the connection
        conn.close();
        return os;
    }	

    

    /**
     *
     * @param size the pizza size
     * @param crust the type of crust
     * @return the base price for a pizza with that size and crust
     * @throws SQLException
     * @throws IOException
     * @requires size = size_s || size_m || size_l || size_xl AND crust = crust_thin || crust_orig || crust_pan || crust_gf
     * @ensures the base price for a pizza with that size and crust is returned
     */
    public static double getBasePrice(String size, String crust) throws SQLException, IOException
    {
        connect_to_db();
        double bp = 0.0;
    
	String query = "Select PRICE From BASE_PRICES where SIZES = ? AND CRUST = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
	stmt.setString(1, size);
	stmt.setString(2, crust);

        try {
            ResultSet rset = stmt.executeQuery();
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {
			bp = rset.getDouble(1);
			}
			
		}
		catch (SQLException e) {
            System.out.println("Error loading BASE_PRICES");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
        }
	conn.close();
        return bp;
}
    /**
     *
     * @return the list of all discounts in the database
     * @throws SQLException
     * @throws IOException
     * @ensures all discounts are included in the returned list
     */
    public static ArrayList<Discount> getDiscountList() throws SQLException, IOException
    {
        ArrayList<Discount> discs = new ArrayList<Discount>();
        connect_to_db();
      
        String query = "Select ID From DISCOUNTS;";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {

                int ID = rset.getInt(1);
                //Now I'm just passing my primary key to this function to get the topping itself individually
                discs.add(getDiscount(ID));
            }
        }
        catch (SQLException e) {
            System.out.println("Error loading DISCOUNTS");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return discs;
        }

        conn.close();
        return discs;
    }


    /**
     *
     * @return the list of all delivery and carry out customers
     * @throws SQLException
     * @throws IOException
     * @ensures the list contains all carryout and delivery customers in the database
     */
    public static ArrayList<ICustomer> getCustomerList() throws SQLException, IOException
    {
        ArrayList<ICustomer> custs = new ArrayList<ICustomer>();
        connect_to_db();
      
        String query = "Select ID From CUSTOMER;";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {

                int ID = rset.getInt(1);
                //Now I'm just passing my primary key to this function to get the topping itself individually
                custs.add(getCustomer(ID));
            }
        }
        catch (SQLException e) {
            System.out.println("Error loading inventory");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return custs;
        }

        conn.close();
        return custs;
    }



	/*
	Note: The following incomplete functions are not strictly required, but could make your DBNinja class much simpler. For instance, instead of writing one query to get all of the information about an order, you can find the primary key of the order, and use that to find the primary keys of the pizzas on that order, then use the pizza primary keys individually to build your pizzas. We are no longer trying to get everything in one query, so feel free to break them up as much as possible

	You could also add functions that take in a Pizza object and add that to the database, or take in a pizza id and a topping id and add that topping to the pizza in the database, etc. I would recommend this to keep your addOrder function much simpler

	These simpler functions should still not be called from our menu class. That is why they are private

	We don't need to open and close the connection in these, since they are only called by a function that has opened the connection and will close it after
	*/

	
    private static Topping getTopping(int ID) throws SQLException, IOException
    {

        //add code to get a topping
		//the java compiler on unix does not like that t could be null, so I created a fake topping that will be replaced
        Topping t = new Topping("fake", 0.25, 100.0, -1);
	String query = "Select NAME, PRICE, INVENTORY From TOPPINGS where ID = " + ID + ";";

        Statement stmt = conn.createStatement();
        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {
					String tname = rset.getString(1);
					double price = rset.getDouble(2);
					double inv = rset.getDouble(3);
					
					t = new Topping(tname, price, inv, ID);
			}
			
		}
	catch (SQLException e) {
            System.out.println("Error loading Topping");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return t;
        }
		
        return t;

    }

    private static int getLastID(String table) throws SQLException, IOException
    {
	int id = -1;
	String query = "SELECT MAX(ID) FROM " + table + ";";
	Statement statement = conn.createStatement();

	try {  
		ResultSet rset = statement.executeQuery(query);
		while(rset.next()){
			id = rset.getInt(1);
		}
	}

	catch (SQLException e) {
            System.out.println("Error loading LASTID");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            conn.close();
            return id;
        }

        return id;
    }

	private static ICustomer getCustomer(int ID) throws SQLException, IOException {

        ICustomer c = null;
	String query = "SELECT TYPE FROM CUSTOMER where ID = " + ID + ";";
        Statement stmt = conn.createStatement();

        try {
            ResultSet rset = stmt.executeQuery(query);
            while(rset.next())
            {
				String type = rset.getString(1);

				if(type.compareTo(DBNinja.delivery) == 0) {

					String query2 = "SELECT Fname, PHONENUM, ADDRESS FROM DELIVERY WHERE ID = ?";
					PreparedStatement statement = conn.prepareStatement(query2);
					statement.setInt(1, ID);

					ResultSet rs = statement.executeQuery();

					rs.next();

					String name = rs.getString(1);
					String number = rs.getString(2);
					String address = rs.getString(3);
					
					c = new DeliveryCustomer(ID, name, number, address);
				}

				if(type.compareTo(DBNinja.pickup) == 0) {
					String query2 = "SELECT Fname, PHONENUM FROM PICKUP WHERE ID = ?";
					PreparedStatement statement = conn.prepareStatement(query2);
					statement.setInt(1, ID);

					ResultSet rs = statement.executeQuery();

					rs.next();

					String name = rs.getString(1);
					String number = rs.getString(2);
					
					c = new DineOutCustomer(ID, name, number);
				}
				
			}
			
		}
	catch (SQLException e) {
            System.out.println("Error loading getCustomer");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return c;
        }
		
        return c;

    }


    private static int basePriceID(String sizes, String crust) throws SQLException, IOException {
	
	int id = -1;
	String query = "SELECT ID FROM BASE_PRICES WHERE SIZES = ? AND CRUST = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
	stmt.setString(1, sizes);
	stmt.setString(2, crust);

	try {  
		ResultSet rset = stmt.executeQuery();
		while(rset.next()){
			id = rset.getInt(1);
		}
	}

	catch (SQLException e) {
            System.out.println("Error loading BASEPRICE");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            conn.close();
        }
		
        return id;
    }

    private static void manyPizzas(ArrayList<Pizza> pizza, int OrderID) throws SQLException, IOException {

        for(Pizza p : pizza){

		int lastID = getLastID("PIZZA");
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		int basePriceID = basePriceID(p.getSize(), p.getCrust());

		String query = "INSERT INTO PIZZA (ID, PRICE, COST_TO_COMPANY, TIME_STAMP, ORDERID, BPID)" + " VALUES (?,?,?,?,?,?);";
		PreparedStatement statement = conn.prepareStatement(query);

		statement.setInt(1, lastID + 1);
		statement.setDouble(2, p.calcPrice());
		statement.setDouble(3, 2.00);
		statement.setString(4, ts.toString());
		statement.setInt(5, OrderID);
		statement.setInt(6, basePriceID);
	
		try {
		    statement.executeUpdate();
	            addToppings(p, lastID + 1);
		}
		catch (SQLException e) {
		    System.out.println("Error loading PIZZAS");
		    while (e != null) {
		        System.out.println("Message     : " + e.getMessage());
		        e = e.getNextException();
		    }
		    conn.close();
		}
		conn.close();	
	}
    }

    public static void addToppings(Pizza pizza, int id) throws SQLException, IOException {
        connect_to_db();

	for(Topping i : pizza.getToppings()){

		String query = "INSERT INTO TOPPINGS_ON_PIZZA(ID, TOPPING_NAME, EXTRA)" + " VALUES (?,?,?);"; 
		PreparedStatement statement = conn.prepareStatement(query);

		statement.setInt(1, id);
		statement.setInt(2, i.getID());
		statement.setBoolean(3, i.getExtra());

		SubtractFromInventory(i);
		
		try {
		    statement.executeUpdate();
		}

		catch (SQLException e) {
		    System.out.println("Error loading ADDTOPPINGS");
		    while (e != null) {
		        System.out.println("Message     : " + e.getMessage());
		        e = e.getNextException();
		    }

		    conn.close();
		    }
		}
		conn.close();
	}


    private static Discount getDiscount(int ID)  throws SQLException, IOException
    {
        Discount d = null;
	String query = "SELECT NAME, PERCENT_OFF, DOLLAR_OFF FROM DISCOUNTS where ID = ?";
	PreparedStatement statement = conn.prepareStatement(query);
	statement.setInt(1, ID);

        try {
            ResultSet rset = statement.executeQuery();	
	    rset.next();
	    String name = rset.getString(1);
	    double percent = rset.getDouble(2);
	    double dollaroff = rset.getDouble(3);	
	    d = new Discount(name, percent, dollaroff, ID);
	}

	catch (SQLException e) {
            System.out.println("Error loading DISCOUNTS");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }
            conn.close();
            return d;
        }		
        return d;
    }
	
    private static Order getOrder(int ID)  throws SQLException, IOException {
	ICustomer c;

	c = new DineOutCustomer(-1, "fillername", "345345345");

        Order o = new Order(-1, c, "delivery");

	String query = "Select TOTALCOSTBUS, TOTALPRICECUSTOMER From ORDERS where ID = " + ID + ";";

        Statement stmt = conn.createStatement();

        try {
            ResultSet rset = stmt.executeQuery(query);
            //even if you only have one result, you still need to call ResultSet.next() to load the first tuple
            while(rset.next())
            {
					double totalBusiness = rset.getDouble(1);
	    				double totalCustomer = rset.getDouble(2);
	    				o = new Order(ID, o.getCustomer(), o.getType());
			}
			
		}
	catch (SQLException e) {
            System.out.println("Error loading getOrder");
            while (e != null) {
                System.out.println("Message     : " + e.getMessage());
                e = e.getNextException();
            }

            //don't leave your connection open!
            conn.close();
            return o;
        }
		
        return o;
    }


/*
    private static Pizza getPizza()  throws SQLException, IOException
    {

        //add code to get Pizza Remember, a Pizza has toppings and discounts on it
        Pizza P;

        return P;

    }

    private static ICustomer getCustomer()  throws SQLException, IOException
    {

        //add code to get customer


        ICustomer C;

        return C;


    }

    private static Order getOrder()  throws SQLException, IOException
    {

        //add code to get an order. Remember, an order has pizzas, a customer, and discounts on it


        Order O;

        return O;

    }
    */

}
