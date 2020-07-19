import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Node {
    private final Integer data;
    private Node parent;
    private Node left;
    private Node right;

}
