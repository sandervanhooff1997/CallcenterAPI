import domain.models.Call;

import domain.models.Company;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static junit.framework.TestCase.assertTrue;

public class BeanValidationTest  {
    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeClass
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    @Test
    public void callBeanTest() {
        Company c = new Company();
        c.setName("djklsadlk sajklahdlkasj dldjhajk sda kljsda kljd alkjd alskdjhal kdjha lkdjh alkjsdh alskd alkjds akdj alkd asdkj ahdlkja dkasjhd alkjsdha lkdsas lkd aslkjd alksdasldk aslkjahsdlk ashd lkajhd alksdh lakdhasklj");

        Set<ConstraintViolation<Company>> violations = validator.validate(c);
        System.out.println(violations.isEmpty());
        assertTrue(violations.isEmpty());
    }
}
