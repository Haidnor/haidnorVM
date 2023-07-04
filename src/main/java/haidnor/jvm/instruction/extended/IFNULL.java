package haidnor.jvm.instruction.extended;

import haidnor.jvm.instruction.Instruction;
import haidnor.jvm.runtime.Frame;
import haidnor.jvm.runtime.StackValue;
import haidnor.jvm.util.CodeStream;

public class IFNULL extends Instruction {
    /**
     * 下次再执行的偏移量
     */
    private final int offSet;

    public IFNULL(CodeStream codeStream) {
        super(codeStream);
        this.offSet = codeStream.readShort(this);
    }

    @Override
    public void execute(Frame frame) {
        StackValue v1 = frame.pop();
        if (v1.getValue() == null) {
            super.setOffSet(offSet);
        }
    }

    @Override
    public String toString() {
        return super.index() + " " + this.getClass().getSimpleName() + " "  + offSet;
    }
}
