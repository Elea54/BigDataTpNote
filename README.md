# Commandes CLI

Des données pour tester sur un petit échantillon sont dans le fichier essai.txt.

### Copier/coller la data dans le conteneur HDFS

    hadoop fs -mkdir /data/relationships

    hadoop fs -put /tmp/data/relationships/data.txt /data/relationships

Pour utiliser les données de test :

    hadoop fs -mkdir /data/relationships

    hadoop fs -put /tmp/data/relationships/essai.txt /data/relationships

### Exécuter le job 1

    hadoop jar /tmp/jars/tpfinal-elea_warskotte_job1.jar /data/relationships/data.txt /data/output/job1

Pour utiliser les données de test

    hadoop jar /tmp/jars/tpfinal-elea_warskotte_job1.jar /data/relationships/essai.txt /data/output/job1

### Voir les résultats du job 1

    hadoop fs -head /data/output/job1/part-r-00000

### Exécuter le job 2

    hadoop jar /tmp/jars/tpfinal-elea_warskotte_job2.jar /data/output/job1 /data/output/job2

### Résultats du job 2

    hadoop fs -head /data/output/job2/part-r-00000

### Exécuter le job 3

    hadoop jar /tmp/jars/tpfinal-elea_warskotte_job3.jar /data/output/job2 /data/output/job3

### Résultats du job 3

    hadoop fs -head /data/output/job3/part-r-00000