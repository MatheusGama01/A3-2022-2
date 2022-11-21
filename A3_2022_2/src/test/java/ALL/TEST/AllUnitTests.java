package ALL.TEST;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import CONTROLLER.TEST.UNIT.ControllerTelaAdicionarTarefaTest;
import CONTROLLER.TEST.UNIT.ControllerTelaCadastroTest;
import CONTROLLER.TEST.UNIT.ControllerTelaHomeTest;
import CONTROLLER.TEST.UNIT.ControllerTelaLoginTest;
import CONTROLLER.TEST.UNIT.ControllerTelaTarefaTest;
import DAO.TEST.UNIT.ConexaoDAOTest;
import DAO.TEST.UNIT.TarefaDAOTest;
import DAO.TEST.UNIT.UsuarioDAOTest;
import HELPER.TEST.UNIT.CriptografiaTest;
import HELPER.TEST.UNIT.ValidacoesTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    ControllerTelaAdicionarTarefaTest.class,
    ControllerTelaCadastroTest.class,
    ControllerTelaHomeTest.class,
    ControllerTelaLoginTest.class,
    ControllerTelaTarefaTest.class,
    ConexaoDAOTest.class,
    TarefaDAOTest.class,
    UsuarioDAOTest.class,
    CriptografiaTest.class,
    ValidacoesTest.class
})
public class AllUnitTests {
    
}
