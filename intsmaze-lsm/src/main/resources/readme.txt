数据存储在磁盘的格式
三种文件，filter(布隆数据),index(索引数据),table(用户数据)

DBInitializer#loadTables
会先读取数据所在文件夹下的所有文件，然后根据文件序号依次遍历
读取索引文件和布隆文件，将里面的数据缓存在FileTable对象中。

List<Table> tables
一个filter和index组成一个table元素