package Test;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import Main.Location;
import Main.MainWindow;


public class TestBasicClass {
	public MainWindow mainWindow;

	@Before
	public void beforeMethod(){
		mainWindow = new MainWindow();
	}


	//test the function getHandShootingLocation works at an angle 0 and 20
	//david
	@Test
	public void testGetHandShootingLocation(){
		//This test makes sure that davids hand can move at two different angles
		Location place1 = mainWindow.playingArea.david.getHandShootingLocation(0);
		Assert.assertEquals(place1.x, 150.0);
		Assert.assertEquals(place1.y, 258.0);

		Location place2 = mainWindow.playingArea.david.getHandShootingLocation(20);
		Assert.assertEquals(place2.x, 142.0);
		Assert.assertEquals(place2.y, 265.0);
	}

	//test class Rock-----------------------------------------------
	//test the function updateRockLocation works
	@Test
	public void testUpdateRockLocation(){
		mainWindow.playingArea.rock.location.setX(50);
		mainWindow.playingArea.rock.location.setY(50);
		mainWindow.playingArea.rock.speed = 75;
		mainWindow.playingArea.rock.angle = 30;
		mainWindow.playingArea.rock.updateRockLocation(mainWindow.playingArea.theGraphics,new Location (0,0));//just added location to this 
		//Test that incrementing the rock by time unit places the rock in the correct next position
		Assert.assertEquals(Math.floor(mainWindow.playingArea.rock.locationForTesting.getX()), 56.0);
		Assert.assertEquals(Math.floor(mainWindow.playingArea.rock.locationForTesting.getY()), 53.0);
	}

	//test class Sling 1of2
	//test the function updateAngle works
	@Test
	public void testUpdateAngle(){
		//test that updating of the sling when changing the launch trajectory
		mainWindow.playingArea.sling.endOfSling.setX(230);
		mainWindow.playingArea.sling.endOfSling.setY(290);
		mainWindow.playingArea.sling.updateAngle(0);
		Assert.assertEquals(mainWindow.playingArea.sling.endOfSling.x,230.0);
		Assert.assertEquals(mainWindow.playingArea.sling.endOfSling.y,290.0);
	}

	//test class Sling 2of2
	//test the function drawNextEndOfSlingLocation works
	@Test
	public void testDrawNextEndOfSlingLocation(){
		//check moving to the left down the sling without an angle
		mainWindow.playingArea.sling.endOfSling.setX(230);
		mainWindow.playingArea.sling.endOfSling.setY(290);
		mainWindow.playingArea.sling.drawNextEndOfSlingLocation(0,5,mainWindow.playingArea.theGraphics,false);//just added boolean to this 
		Assert.assertEquals(mainWindow.playingArea.sling.endOfSling.getX(), 225.0);
		Assert.assertEquals(mainWindow.playingArea.sling.endOfSling.getY(), 290.0);

		//check moving to the left down the sling with an angle
		mainWindow.playingArea.sling.endOfSling.setX(70);
		mainWindow.playingArea.sling.endOfSling.setY(290);
		mainWindow.playingArea.sling.drawNextEndOfSlingLocation(0,5,mainWindow.playingArea.theGraphics,false);//just added boolean to this 
		Assert.assertEquals(mainWindow.playingArea.sling.endOfSling.getX(), 75.0);
		Assert.assertEquals(mainWindow.playingArea.sling.endOfSling.getY(), 290.0);
	}

	//test class PlayingArea
	@Test
	public void testSetAngle(){
		//these tests make sure that the launching angle can be set correct
		//test 45 degree angle
		mainWindow.playingArea.lineStart.setX(15);
		mainWindow.playingArea.lineStart.setY(0);
		mainWindow.playingArea.lineEnd.setX(0);
		mainWindow.playingArea.lineEnd.setY(15);
		mainWindow.playingArea.setAngle();
		Assert.assertEquals(mainWindow.playingArea.angle, 45);

		//test 0 degree angle
		mainWindow.playingArea.lineStart.setX(15);
		mainWindow.playingArea.lineStart.setY(0);
		mainWindow.playingArea.lineEnd.setX(0);
		mainWindow.playingArea.lineEnd.setY(0);
		mainWindow.playingArea.setAngle();
		Assert.assertEquals(mainWindow.playingArea.angle, 0);

		//test 30 degree angle   30 60 90 triangle
		mainWindow.playingArea.lineStart.setX(86);//15
		mainWindow.playingArea.lineStart.setY(0);
		mainWindow.playingArea.lineEnd.setX(0);
		mainWindow.playingArea.lineEnd.setY(50);
		mainWindow.playingArea.setAngle();
		Assert.assertEquals(mainWindow.playingArea.angle, 30);
	}


}