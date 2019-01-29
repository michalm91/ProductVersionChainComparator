public final class ProductVersionChainComparator
{
	public final static String EXCEPTION_NULL_VERSION = "No set ProductVersion instance";

    private LogicalOperation   logicalOperation;
    private Boolean            result;

    public static ProductVersionChainComparatorBuilder of(ProductVersion productVersion)
    {
        return new ProductVersionChainComparator.ProductVersionChainComparatorBuilder(productVersion);
    }

    private ProductVersionChainComparator()
    {}

    private void calculateResult(boolean methodResult)
    {
        if (getResult() == null)
        {
            setResult(methodResult);
            return;
        }
        setResult(logicalOperation.calculate(getResult(), methodResult));
    }

    private Boolean getResult()
    {
        return result;
    }

    private void setLogicalOperation(LogicalOperation logicalOperation)
    {
        this.logicalOperation = logicalOperation;
    }

    private void setResult(Boolean result)
    {
        this.result = result;
    }
	
	
    public static class ProductVersionChainComparatorBuilder
    {
        private ProductVersionChainComparator instance = new ProductVersionChainComparator();
        private ProductVersion                productVersion;

        private ProductVersionChainComparatorBuilder(ProductVersion productVersion)
        {
            if (productVersion == null) throw new NullPointerException(EXCEPTION_NULL_VERSION);
            this.productVersion = productVersion;
        }

        public ProductVersionChainComparatorBuilder and()
        {
            instance.setLogicalOperation(LogicalOperation.AND);
            return this;
        }

        public boolean check()
        {
            return instance.getResult();
        }

        public ProductVersionChainComparatorBuilder isEquals(int major, int minor, int revision, int build)
        {
            instance.calculateResult(productVersion.isEquals(major, minor, revision, build));
            return this;
        }

        public ProductVersionChainComparatorBuilder isNewerOrEqual(int major, int minor, int revision, int build)
        {
            instance.calculateResult(productVersion.isNewerOrEqual(major, minor, revision, build));
            return this;
        }

        public ProductVersionChainComparatorBuilder isNewerThan(int major, int minor, int revision, int build)
        {
            instance.calculateResult(productVersion.isNewerThan(major, minor, revision, build));
            return this;
        }

        public ProductVersionChainComparatorBuilder isOlderOrEqual(int major, int minor, int revision, int build)
        {
            instance.calculateResult(productVersion.isOlderOrEqual(major, minor, revision, build));
            return this;
        }

        public ProductVersionChainComparatorBuilder isOlderThan(int major, int minor, int revision, int build)
        {
            instance.calculateResult(productVersion.isOlderThan(major, minor, revision, build));
            return this;
        }

        public ProductVersionChainComparatorBuilder isNotEquals(int major, int minor, int revision, int build)
        {
            instance.calculateResult(productVersion.isNotEquals(major, minor, revision, build));
            return this;
        }

        public ProductVersionChainComparatorBuilder or()
        {
            instance.setLogicalOperation(LogicalOperation.OR);
            return this;
        }

    }

    private enum LogicalOperation
    {
        AND()
        {
            @Override
            public boolean calculate(boolean val1, boolean val2)
            {
                return Boolean.logicalAnd(val1, val2);
            }

        },

        OR()
        {
            @Override
            public boolean calculate(boolean val1, boolean val2)
            {
                return Boolean.logicalOr(val1, val2);
            }
        };

        public abstract boolean calculate(boolean val1, boolean val2);
    }

    
}