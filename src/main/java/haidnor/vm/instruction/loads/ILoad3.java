package haidnor.vm.instruction.loads;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;

/**
 * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iload_n
 */
@Slf4j
public class ILoad3 extends Instruction {

    public ILoad3(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {
        int value = frame.slotGetInt(3);
        frame.push(new StackValue(Const.T_INT, value));
    }

}
