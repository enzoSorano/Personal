//structs
typedef struct tnode{
  double weight;
  int c;
  struct tnode* left;
  struct tnode* right;
  struct tnode* parent;
}tnode;


//Prototypes
tnode* createFreqTable(char* fileName);
tnode* createHuffmanTree(tnode* leaves);
void encodeFile(char* filename, tnode* leaves);
void decodeFile(char* fileName, tnode* treeRoot);
void freeTree(tnode* node);
