public class Planet
{
	public double x;
	public double y;
	public double xVelocity;
	public double yVelocity;
	public double xAccel;
	public double yAccel;
	public double xNetForce;
	public double yNetForce;
	public double mass;
	public String img;

	public Planet(double x, double y, double xVelocity, double yVelocity,
		          double mass, String img)
	{
		this.x = x;
		this.y = y;
		this.xVelocity = xVelocity;
		this.yVelocity = yVelocity;
		this.mass = mass;
		this.img = "images/" + img;
	}

	public double calcDistance(Planet p)
	{
		return Math.hypot(x - p.x, y - p.y);
	}

	public double calcPairwiseForce(Planet p)
	{
		final double G_CONSTANT = 6.67e-11;
		double distance = calcDistance(p);

		return (G_CONSTANT * mass * p.mass / (distance * distance));
	}

	public double calcPairwiseForceX(Planet p)
	{
		double deltaX = p.x - x,
		       distance = calcDistance(p),
		       netForce = calcPairwiseForce(p);

		return netForce * deltaX / distance;
	}

	public double calcPairwiseForceY(Planet p)
	{
		double deltaY = p.y - y,
		       distance = calcDistance(p),
		       netForce = calcPairwiseForce(p);

		return netForce * deltaY / distance;
	}

	public void setNetForce(Planet[] planets)
	{
		xNetForce = 0;
		yNetForce = 0;

		for (int i = 0; i < planets.length; i++)
		{
			if (!this.equals(planets[i]))
			{
				xNetForce += calcPairwiseForceX(planets[i]);
				yNetForce += calcPairwiseForceY(planets[i]);
			}
		}
	}
	
	public void draw()
	{
		StdDraw.picture(x, y, img);
	}

	public void update(double dt)
	{
		xAccel = xNetForce / mass;
		yAccel = yNetForce / mass;

		xVelocity = xVelocity + dt * xAccel;
		yVelocity = yVelocity + dt * yAccel;

		x = x + dt * xVelocity;
		y = y + dt * yVelocity;
	}
}