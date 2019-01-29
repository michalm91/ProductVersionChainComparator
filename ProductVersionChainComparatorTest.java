import org.junit.Test;
import static org.assertj.core.api.Assertions.*;


public class ProductVersionChainComparatorTest
{
    private final static int MAJOR1      = 2;
    private final static int MAJOR2      = 3;
    private final static int MAJOR3      = 4;
    private final static int MINOR       = 5;
    private final static int REVISION    = 1;
    private final static int BUILD 		 = 165;

    @Test
    public void return_exception_when_null_version()
    {
        assertThatThrownBy(() -> ProductVersionChainComparator.of(null)
		.isNewerOrEqual(0, 0, 0, 0).and()
		.isNewerThan(0, 0, 0, 0).or()
		.isOlderOrEqual(0, 0, 0, 0)
        .isOlderThan(0, 0, 0, 0)
		.isNotEquals(0, 0, 0, 0)
		.isEquals(0, 0, 0, 0)
		.check())
        .isInstanceOf(NullPointerException.class).hasMessageContaining(ProductVersionChainComparator.EXCEPTION_NULL_VERSION);
    }

    @Test
    public void return_false_when_version_is_not_equals_between_newer_and_older()
    {
        assertThat(ProductVersionChainComparator.of(getExampleVersion())
			.isNewerThan(MAJOR1, MINOR, REVISION,BUILD).and()
			.isOlderThan(MAJOR2, MINOR, REVISION, BUILD).check()).isEqualTo(false);
    }

    @Test
    public void return_true_when_is_equals_version()
    {
        assertThat(ProductVersionChainComparator.of(getExampleVersion())
			.isEquals(MAJOR2, MINOR, REVISION,BUILD).check()).isEqualTo(true);
    }

    @Test
    public void return_true_when_is_not_equals_version()
    {
        assertThat(ProductVersionChainComparator.of(getExampleVersion())
			.isNotEquals(MAJOR1, MINOR, REVISION,BUILD).check()).isEqualTo(true);
    }


    @Test
    public void return_true_when_version_is_equals_between_newer_and_older()
    {
        assertThat(ProductVersionChainComparator.of(getExampleVersion())
			.isNewerThan(MAJOR1, MINOR, REVISION,BUILD).and()
            .isOlderThan(MAJOR3, MINOR, REVISION, BUILD).check()).isEqualTo(true);
    }

    @Test
    public void return_true_when_version_is_equals_between_newerOrEqual_or_olderOrEqual()
    {
        assertThat(ProductVersionChainComparator.of(getExampleVersion())
			.isNewerOrEqual(MAJOR3, MINOR, REVISION,BUILD).or()
            .isOlderOrEqual(MAJOR3, MINOR, REVISION, BUILD).check()).isEqualTo(true);
    }

    private ProductVersion getExampleVersion()
    {
        return new ProductVersionImpl(MAJOR2, MINOR, REVISION, BUILD);
    }

}