#include <iostream>

template <typename T>
struct TreeNode {
    T data;
    TreeNode<T>* left;
    TreeNode<T>* right;

    TreeNode(const T& value) : data(value), left(nullptr), right(nullptr) {}
};

template <typename T>
class BST {
private:
    TreeNode<T>* root;

    void InsertHelper(TreeNode<T>*& node, const T& value) {
        if (node == nullptr) {
            node = new TreeNode<T>(value);
        } else if (value < node->data) {
            InsertHelper(node->left, value);
        } else if (value > node->data) {
            InsertHelper(node->right, value);
        }
    }

    bool SearchHelper(TreeNode<T>* node, const T& value) const {
        if (node == nullptr) {
            return false;
        } else if (value == node->data) {
            return true;
        } else if (value < node->data) {
            return SearchHelper(node->left, value);
        } else {
            return SearchHelper(node->right, value);
        }
    }

    void PreorderHelper(TreeNode<T>* node) const {
        if (node != nullptr) {
            std::cout << node->data << "\t";
            PreorderHelper(node->left);
            PreorderHelper(node->right);
        }
    }

    void PostorderHelper(TreeNode<T>* node) const {
        if (node != nullptr) {
            PostorderHelper(node->left);
            PostorderHelper(node->right);
            std::cout << node->data << "\t";
        }
    }

    void InorderHelper(TreeNode<T>* node) const {
        if (node != nullptr) {
            InorderHelper(node->left);
            std::cout << node->data << "\t";
            InorderHelper(node->right);
        }
    }

    int CountNodesHelper(TreeNode<T>* node) const {
        if (node == nullptr) {
            return 0;
        }
        return 1 + CountNodesHelper(node->left) + CountNodesHelper(node->right);
    }

    int CountLeafNodesHelper(TreeNode<T>* node) const {
        if (node == nullptr) {
            return 0;
        } else if (node->left == nullptr && node->right == nullptr) {
            return 1;
        }
        return CountLeafNodesHelper(node->left) + CountLeafNodesHelper(node->right);
    }

    int CountParentNodesHelper(TreeNode<T>* node) const {
        if (node == nullptr || (node->left == nullptr && node->right == nullptr)) {
            return 0;
        }
        return 1 + CountParentNodesHelper(node->left) + CountParentNodesHelper(node->right);
    }

public:
    BST() : root(nullptr) {}

    void Insert(const T& value) {
        InsertHelper(root, value);
    }

    bool Search(const T& value) const {
        return SearchHelper(root, value);
    }

    void Preorder() const {
        PreorderHelper(root);
    }

    void Postorder() const {
        PostorderHelper(root);
    }

    void Inorder() const {
        InorderHelper(root);
    }

    int CountNodes() const {
        return CountNodesHelper(root);
    }

    int CountLeafNodes() const {
        return CountLeafNodesHelper(root);
    }

    int CountParentNodes() const {
        return CountParentNodesHelper(root);
    }
};

int main() {
    BST<int> bst;

    bst.Insert(21);
    bst.Insert(23);
    bst.Insert(15);
    bst.Insert(78);
    bst.Insert(45);
    bst.Insert(10);
    bst.Insert(90);
    bst.Insert(18);
    bst.Insert(7);

    std::cout << "Elements in Preorder format : ";
    bst.Preorder();
    std::cout << std::endl;

    std::cout << "Elements in Postorder format : ";
    bst.Postorder();
    std::cout << std::endl;

    std::cout << "Elements in Inorder format : ";
    bst.Inorder();
    std::cout << std::endl;

    if (bst.Search(516)) {
        std::cout << "Element is there in BST\n";
    } else {
        std::cout << "Element is not there in BST\n";
    }

    std::cout << "Number of nodes are : " << bst.CountNodes() << std::endl;
    std::cout << "Number of leaf nodes are : " << bst.CountLeafNodes() << std::endl;
    std::cout << "Number of parent nodes are : " << bst.CountParentNodes() << std::endl;

    return 0;
}
