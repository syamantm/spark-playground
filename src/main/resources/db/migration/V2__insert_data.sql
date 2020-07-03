INSERT INTO HIVE_QUERY (dataset_name, query, count_column, min_val, max_val)
VALUES
	('activities','SELECT count(impressions) AS total_impressions FROM reporting_db.activities','total_impressions',3,100),
	('sample_ds','SELECT count(*) AS total_count FROM sample_ds.my_table','total_count',20,500);
