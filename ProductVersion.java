import java.io.*;


public interface ProductVersion
{
    default public boolean isEquals(int major, int minor, int revision, int build)
    {
        if ((getMajor() == major) && (getMinor() == minor) && (getRevision() == revision) && (getBuild() == build)) return true;

        return false;
    }


    default public boolean isEquals(ProductVersion version)
    {
        if (version == null) return false;

        return isEquals(version.getMajor(), version.getMinor(), version.getRevision(), version.getBuild());
    }


    default public boolean isNewerOrEqual(int major, int minor, int revision, int build)
    {
        return isNewerThan(major, minor, revision, build) || isEquals(major, minor, revision, build);
    }


    default public boolean isNewerThan(int major, int minor, int revision, int build)
    {
        if (getMajor() > major) return true;
        if (getMajor() < major) return false;

        if (getMinor() > minor) return true;
        if (getMinor() < minor) return false;

        if (getRevision() > revision) return true;
        if (getRevision() < revision) return false;

        if (getBuild() > build) return true;
        if (getBuild() < build) return false;

        return false;
    }


    default public boolean isNewerThan(ProductVersion version)
    {
        if (version == null) return true;

        return isNewerThan(version.getMajor(), version.getMinor(), version.getRevision(), version.getBuild());
    }


    default public boolean isOlderOrEqual(int major, int minor, int revision, int build)
    {
        return isOlderThan(major, minor, revision, build) || isEquals(major, minor, revision, build);
    }


    default public boolean isOlderThan(int major, int minor, int revision, int build)
    {
        if (getMajor() > major) return false;
        if (getMajor() < major) return true;

        if (getMinor() > minor) return false;
        if (getMinor() < minor) return true;

        if (getRevision() > revision) return false;
        if (getRevision() < revision) return true;

        if (getBuild() > build) return false;
        if (getBuild() < build) return true;

        return false;
    }


    default public boolean isNotEquals(int major, int minor, int revision, int build)
    {
        return !isEquals(major, minor, revision, build);
    }
	
	
	
	public int getBuild();

    public int getMajor();

    public int getMinor();

    public int getRevision();
	
	public void setBuild(int build);

    public void setMajor(int major);

    public void setMinor(int minor);

    public void setRevision(int revision);
}
