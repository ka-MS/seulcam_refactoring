package com.kh.seulcam.product.service.logic;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.kh.seulcam.product.domain.Brand;
import com.kh.seulcam.product.domain.Detail;
import com.kh.seulcam.product.domain.Product;
import com.kh.seulcam.product.domain.Review;
import com.kh.seulcam.product.service.ProductService;
import com.kh.seulcam.product.store.ProductStore;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	private SqlSessionTemplate session;
	@Autowired
	private ProductStore pStore;
	
	@Override
	public int registerbrand(Brand brand) {
		int result = pStore.insertBrand(session, brand);
		return result;
	}

	@Override
	public List<HashMap> getTotalStoreName() {
		List<HashMap> sNameList=pStore.selectAllStoreName(session);
		return sNameList;
	}

	@Override
	public int registerProduct(Product product) {
		pStore.insertProduct(session, product);
		int productNo = product.getProductNo();
		return productNo;
	}

	@Override
	public List<Product> getTotalProduct(String sortCd) {
		List<Product> pList = pStore.selectAllProduct(session, sortCd);
		return pList;
	}

	@Override
	public int registerProductDetail(Detail detail) {
		int result = pStore.insertProductDetail(session, detail);
		return result;
	}


	@Override
	public List<Product> getProductListByArrayDf(String arrayCd) {
		 List<Product> pList = pStore.selectAllProductByArrayDf(session, arrayCd);
		return pList;
	}

	@Override
	public Product getProductByNo(Integer productNo) {
		Product product = pStore.selectProductByNo(session, productNo);
		return product;
	}

	@Override
	public List<Detail> printAllDetailInfo(Integer productNo) {
		List<Detail> dList = pStore.selectAllDetailInfo(session, productNo);
		return dList;
	}

	@Override
	public int registerProductReview(Review review) {
		int result = pStore.insertProductReview(session, review);
		return result;
	}

	@Override
	public List<Review> getReviewByProductNo(Integer productNo,int currentPage, int boardLimit) {
		List<Review> rList=pStore.selectReviewByProductNo(session, productNo,currentPage,boardLimit);
		return rList;
	}

	@Override
	public List<Brand> getbrandStore(String brandName) {
		List<Brand> bsList = pStore.selectOneBrand(session, brandName);
		return bsList;
	}

	@Override
	public Review getOneReview(Integer reviewNo) {
		Review review = pStore.selectOneReview(session, reviewNo);
		return review;
	}

	@Override
	public String findProductName(int productNo) {
		String productName = pStore.selectProductName(session, productNo);
		return productName;
	}

	@Override
	public int modifyProductReview(Review newReview) {
		int result = pStore.updateProductReview(session, newReview);
		return result;
	}

	@Override
	public int removeReview(Integer reviewNo) {
		int result = pStore.deleteReview(session, reviewNo);
		return result;
	}

	@Override
	public List<Product> findProductByKeyword(String keyword) {
		List<Product> pList = pStore.selectProductByKeyword(session, keyword);
		return pList;
	}

	@Override
	public List<Product> findProductByBrand(String brandName, String sortCd) {
		List<Product> pList=pStore.selectProductByBrand(session, brandName, sortCd);
		return pList;
	}

	@Override
	public List<Product> findProductByCategory(String cate_no, String sortCd) {
		List<Product> pList=pStore.selectProductByCetegory(session, cate_no, sortCd);
		return pList;
	}

	@Override
	public List<Review> getReviewByMemberId(String memberId) {
		List<Review> rList = pStore.selectReviewBymemberId(session, memberId);
		return rList;
	}

	@Override
	public List<Review> getAllReview() {
		List<Review> rList = pStore.selectAllReview(session);
		return rList;
	}

	@Override
	public List<Brand> getAllBrand() {
		List<Brand> bList = pStore.selectAllBrand(session);
		return bList;
	}

	@Override
	public int removeStore(int storeNo) {
		int result = pStore.deleteStore(session, storeNo);
		return result;
	}

	@Override
	public int modifyProduct(Product product) {
		int productNo = pStore.updateProduct(session, product);
		return productNo;
	}

	@Override
	public int modifyProductDetail(Detail dt) {
		int result = pStore.updateProductDetail(session, dt);
		return result;
	}

	@Override
	public int removeProduct(Integer productNo) {
		int result = pStore.deleteProduct(session, productNo);
		return result;
	}

	@Override
	public void removeDetail(Detail dt) {
		pStore.deleteDetail(session, dt);
			
	}

	@Override
	public List<Integer> discountList(List<Product> pList) {
		List<Integer> resultPrice = new ArrayList<Integer>();
		for(int i=0; i<pList.size(); i++) {
			double percent= pList.get(i).getDiscount()*0.01;
			double discount=pList.get(i).getProductPrice()*percent;
			double dPrice = pList.get(i).getProductPrice()-discount;
			
			int iPrice =(int)Math.round(dPrice/1000.0)*1000;
			resultPrice.add(iPrice);
		}
		return resultPrice;
	}
	@Override
	public int discountProduct(Product product) {
		double percent= product.getDiscount()*0.01;
		double discount=product.getProductPrice()*percent;
		double dPrice = product.getProductPrice()-discount;
		
		int resultPrice =(int)Math.round(dPrice/1000.0)*1000;
		
		return resultPrice;
	}

	@Override
	public int getReviewTotalCount(Integer productNo) {
		int totalCount = pStore.selectReviewTotalCount(session,productNo);
		return totalCount;
	}
}
