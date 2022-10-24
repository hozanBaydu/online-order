# online-order library
## Hozan BAYDU

Merhaba,ben Hozan.Bu kütüphaneyi online sipariş sistemleri için geliştirilecek uygulamaların yazımında kolaylık sağlanması için yazdım.  


## Özellikler

- Uygulama içinde ürün ve fotoğraf ismi recyclerviewde gösterilebilir.
- Sqlite kaydedilme işlemi room kütüphanesi ile yapılmıştır.
- Room kütüphanesi ile yapılan işlemler Rxjava2 kullanılarak yapılmıştır.
- İşlemler asekron yapıldığı için kullanıcı arayüzüne zarar vermemektedir.


> Note:  Bu dosyanın ortalama okunma süresi 8 dakika kütüphanenin yüklenmesi 3 dakika sürecektir.


## Kurulum

### 1.Adım

build.gradle (Module:.app) dosyasında dependencies altına

```sh
implementation 'com.github.hozanBaydu:online-order:1.9.1'
```

kodunu yapıştırmanız gerekiyor.

### 2.Adım

settings.gradle dosyasında dependencyResolutionManagement altına

```sh
maven { url 'https://www.jitpack.io' }
```

kodunu yapıştırdıktan sonra kütüphane artık kullanıma hazır.2.adım mevcut 24/10/2022 tarihli
androidstudio sürümü için geçerli olup,ileride gelecek güncellemeler ile bu adımın değişme olasılığı bulunmaktadır.Güncelleme olması durumunda lütfen gerekli araştırmayı yapıp güncel
durumu öğrenin.

## Kullanım

#### Verilerin kaydedilmesi

Kütüphane eklendikten sora yapılacak ilk adım RxRoom sınıfından bir obje oluşturmaktır.
Ardından kütüphane ile birlikte gelecek olan modelimiz için bir değişken oluşturmak.

```sh
val elma=OnlineOrdersFoodsModel("elma","ekşi", R.drawable.thumb)
val cilek=OnlineOrdersFoodsModel("çilek","tatlı", R.drawable.apple)
```

Modelimize uygun bir değişken oluşturduktan sonra artık veri tabanına bunu kaydedebiliriz.

```sh
val rxRoom=RxRoom()
rxRoom.onlineOrderinsert(context = applicationContext,elma)
rxRoom.onlineOrderinsert(context = applicationContext,cilek)
```

Bu fonksiyon sayesinde modellerimiz sqlite veri tabanına kaydedilmiş oldu.

#### Verilerin silinmesi


```sh
 rxRoom.onlineOrderdeleteAll(applicationContext)
```

İstenen Context girildiği zaman onlineOrderdeleteAll fonksiyonu ile birlikte veriler güvenli bir şekilde silinebilmektedir.

#### Verilerin gösterilmesi

Verilerin çekilip gösterilmesi işlemi OnlineOrderGetSqlite sınıfı ile mümkündür.
Bu sınıf bir Context ve içinde verilerin gösterileceği bir recyclerview istemektedir.
Verilerin gösterilmesini istediğiniz aktivitede bir recyclerview üretmeniz yeterli olacaktır.

> Note:  Aadapter veya recycler_row oluşturmanıza gerek yoktur.Kütüphane bunları barındırmaktadır.


```sh
 val showRecyclerview=OnlineOrderGetSqlite(context = applicationContext,recyclerview)
```

Yukarıdaki kodlar,sınıfi çağırıp recyclerviewin tanımlanması için yazılmıştır.

```sh
showRecyclerview.onlineOrdergetAll()
```

Ardından onlineOrdergetAll() fonksiyonu sayesinde recyclerviewde veriler gösterilir.

##### Merak edenler için kütüphanenin bazı sınıfları

```sh
class OnlineOrderGetSqlite(val context: Context,val view: RecyclerView) {
    private var compositeDisposable= CompositeDisposable()
    private lateinit var dp:OnlineOrderFoodsDataBase
    private lateinit var foodDao:OnlineOrderFoodsDao
    fun onlineOrdergetAll(){
        dp= Room.databaseBuilder(context,OnlineOrderFoodsDataBase::class.java,"OnlineOrdersFoodsModel").build()
        foodDao=dp.OnlineOrderFoodsDao()
        compositeDisposable.add(
            foodDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
    }
    fun handleResponse (list: MutableList<OnlineOrdersFoodsModel>){
        view.layoutManager= LinearLayoutManager(context)
        val foodsAdapter= OnlineOrderFoodsAdapter(list)
        view.adapter=foodsAdapter
    }
}
```

Yukarıdaki kütüphane kodları veriler alındıktan sonra recyclerviewde handleresponse 
fonksiyonu ile nasıl gösterildiğini anlatmaktadır.Görüldüğü üzere LinearLayoutManager için context burada verilmeyip kullanıcı sınıfı çağırınca kullanıcıdan istenmektedir.Kullanıcı kütüphaneyi aktivite dışında bir yerde recyclerview oluşturmak için çağırabilir.
.
#### Sonuç



![giriş sayfası](https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEhCpSUxdvgATE_fImDDdFhQ_ShD6NkZz_-4eU_eS2u_fdxESrX-AExx7qLH52T8y4u9LraScyOBHZiZpam9Gj1EUhWfcRBgvfEk114CxF7ezVztYomI1m7OoaYjpa2fc4RcHk6J7JYr_Ekwp5R1RfXQ2E3p-c8cG-eakwpQbsw3ETPqKpftdQPPIh1Y/s400/online.jpeg)

Uygulamayı yazan:Hozan BAYDU
Tarih:20.08.2022
iletişim:hozan.baydu3447@gmail.com
