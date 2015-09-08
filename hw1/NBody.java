public class NBody
{
	public static void main(String[] args)
	{
		double T = Double.parseDouble(args[0]),
		       dt = Double.parseDouble(args[1]);
		String filename = args[2];

		In fin = new In(filename);

		int numberOfPlanets = fin.readInt();
		double radiusOfUniverse = fin.readDouble();

		Planet[] p = new Planet[numberOfPlanets];
		for (int i = 0; i < numberOfPlanets; i++)
			p[i] = getPlanet(fin);

		StdDraw.setScale(radiusOfUniverse * -1, radiusOfUniverse);
		StdAudio.play("audio/2001.mid");

		for (double time = 0; time < T; time += dt)
		{
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (int i = 0; i < numberOfPlanets; i++)
			{
				p[i].setNetForce(p);
				p[i].update(dt);
				p[i].draw();
			}
			StdDraw.show(10);
		}

		StdAudio.close();
	}

	public static Planet getPlanet(In fin)
	{
		return new Planet(fin.readDouble(), fin.readDouble(),
			              fin.readDouble(), fin.readDouble(),
			              fin.readDouble(), fin.readString());
	}
}