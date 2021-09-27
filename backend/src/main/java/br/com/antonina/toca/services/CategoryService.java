package br.com.antonina.toca.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.antonina.toca.dto.CategoryDTO;
import br.com.antonina.toca.entities.Category;
import br.com.antonina.toca.repositories.CategoryRepository;
import br.com.antonina.toca.services.exceptions.DataBaseException;
import br.com.antonina.toca.services.exceptions.ResourceNotFoundExecption;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public Page<CategoryDTO> findAllPaged(Pageable pageable){
		Page<Category> list = repository.findAll(pageable);
		return list.map(x -> new CategoryDTO(x));
		
	
	/*public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();

		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
		*/
		/*
		 * List<CategoryDto> listDto = new ArrayList<>();
		 * 
		 * for (Category cat : list) { listDto.add(new CategoryDto(cat)); } return
		 * listDto;
		 */
	}

	/*
	 * Busca por Id as Categorias
	 */
	@Transactional(readOnly = true)
	public CategoryDTO findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow(() -> new ResourceNotFoundExecption("Categoria não encontrada"));
		return new CategoryDTO(entity);
	}

	/*
	 * Inserção de nova Categoria
	 */
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO(entity);

	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getOne(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundExecption("Id (" + id + ") não existe ");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundExecption("Id (" + id + ") não existe ");
		}
		catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Categoria não pode ser excluida");
		}
	}
}









