#define CONVERSIONS_H
//-------------------
//MARK: Function Prototypes
//All conversions supported to USD
float euro(float toUSD);
float pounds(float toUSD);
float auDollar(float toUSD);
float renminbi(float toUSD);
float lira(float toUSD);
float real(float toUSD);
float lev(float toUSD);

//All conversions supported from USD
float usd_Euro(float amount);
float usd_Pounds(float amount);
float usd_AuDollar(float amount);
float usd_Renminbi(float amount);
float usd_Lira(float amount);
float usd_Real(float amount);
float usd_Lev(float amount);

//Other Functions
void usd_toAll(float amount);
void all_toUSD(float amount);
void print_rates();
