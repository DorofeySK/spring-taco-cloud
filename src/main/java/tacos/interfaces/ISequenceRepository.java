package tacos.interfaces;

public interface ISequenceRepository {
	Long getNextTacoSequence();
	Long getNextTacoOrderSequence();
}
