package me.mrCookieSlime.Slimefun;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.MaterialData;

import me.mrCookieSlime.CSCoreLibPlugin.PlayerRunnable;
import me.mrCookieSlime.CSCoreLibPlugin.general.Chat.TellRawMessage;
import me.mrCookieSlime.CSCoreLibPlugin.general.Chat.TellRawMessage.HoverAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuClickHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu.MenuOpeningHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ClickAction;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.CustomBookOverlay;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.Item.CustomItem;
import me.mrCookieSlime.CSCoreLibPlugin.general.Math.DoubleHandler;
import me.mrCookieSlime.CSCoreLibPlugin.general.String.StringUtils;
import me.mrCookieSlime.CSCoreLibPlugin.general.World.CustomSkull;
import me.mrCookieSlime.Slimefun.GitHub.Contributor;
import me.mrCookieSlime.Slimefun.GitHub.IntegerFormat;
import me.mrCookieSlime.Slimefun.Lists.RecipeType;
import me.mrCookieSlime.Slimefun.Misc.BookDesign;
import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.LockedCategory;
import me.mrCookieSlime.Slimefun.Objects.Research;
import me.mrCookieSlime.Slimefun.Objects.SeasonCategory;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunGadget;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunItem;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.SlimefunMachine;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AGenerator;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AReactor;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineFuel;
import me.mrCookieSlime.Slimefun.Setup.Messages;
import me.mrCookieSlime.Slimefun.Setup.SlimefunManager;
import me.mrCookieSlime.Slimefun.URID.URID;
import me.mrCookieSlime.Slimefun.api.GuideHandler;
import me.mrCookieSlime.Slimefun.api.Slimefun;

public class SlimefunGuide {
	
	public static Map<UUID, List<URID>> history = new HashMap<UUID, List<URID>>();
	public static int month = 0;
	
	public static List<Contributor> contributors = new ArrayList<Contributor>();
	public static int issues = 0;
	public static int forks = 0;
	public static int code_bytes = 0;
	public static Date last_update = new Date();

	static boolean all_recipes = true;
	private static final int category_size = 36;

	@Deprecated
	public static ItemStack getItem() {
		return getItem(BookDesign.CHEST);
	}

	public static ItemStack getItem(BookDesign design) {
		switch (design) {
		case BOOK: {
			return new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&aSlimefun 가이드 &7(책 GUI)", "", "&e우 클릭 &8\u21E8 &7아이템 목록을 봅니다", "&e쉬프트 + 우 클릭 &8\u21E8 &7설정 / 제작자 목록");
		}
		case CHEAT_SHEET: {
			return new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&cSlimefun 가이드 &4(치트 GUI)", "", "&4&l관리자만 열 수 있습니다", "", "&e우 클릭 &8\u21E8 &7아이템 목록을 봅니다", "&e쉬프트 + 우 클릭 &8\u21E8 &7설정 / 제작자 목록");
		}
		case CHEST: {
			return new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&aSlimefun 가이드 &7(상자 GUI)", "", "&e우 클릭 &8\u21E8 &7아이템 목록을 봅니다", "&e쉬프트 + 우 클릭 &8\u21E8 &7설정 / 제작자 목록");
		}
		default:
			return null;
		}
	}

	@Deprecated
	public static ItemStack getItem(boolean book) {
		return getItem(book ? BookDesign.BOOK: BookDesign.CHEST);
	}
	
	public static ItemStack getDeprecatedItem(boolean book) {
		return new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&eSlimefun 가이드 &7(우 클릭)", (book ? "": "&2"), "&r이것은 Slimefun 을 위한 기본 지침서입니다", "&r이 플러그인으로 추가된 모든 항목을 볼 수 있습니다", "&r요리법을 포함한 애드온 목록", "&r그 외 정보를 볼 수 있습니다");
	}
	
	private static final int[] slots = new int[] {0, 2, 3, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35};
	
	@SuppressWarnings("deprecation")
	public static void openSettings(Player p, final ItemStack guide) {
		final ChestMenu menu = new ChestMenu("설정 / 정보");
		
		menu.addMenuOpeningHandler(new MenuOpeningHandler() {
			
			@Override
			public void onOpen(Player p) {
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HARP, 0.7F, 0.7F);
			}
		});
		
		for (int i: slots) {
			menu.addItem(i, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 7), " "));
			menu.addMenuClickHandler(i, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}
			});
		}
		
		
		if (SlimefunManager.isItemSimiliar(guide, getItem(BookDesign.CHEST), true)) {
			if (p.hasPermission("slimefun.cheat.items")) {
				menu.addItem(19, new CustomItem(new MaterialData(Material.CHEST), "&7가이드 나열 방식: &e상자 GUI", "", "&a상자 GUI", "&7책 GUI", "&7치트 GUI", "", "&e 클릭 &8\u21E8 &7나열 방식을 변경합니다"));
				menu.addMenuClickHandler(19, new MenuClickHandler() {
					
					@Override
					public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
						p.getInventory().setItemInMainHand(getItem(BookDesign.BOOK));
						openSettings(p, p.getInventory().getItemInMainHand());
						return false;
					}
				});
			}
			else {
				menu.addItem(19, new CustomItem(new MaterialData(Material.CHEST), "&7가이드 나열 방식: &e상자 GUI", "", "&a상자 GUI", "&7책 GUI", "", "&e 클릭 &8\u21E8 &7나열 방식을 변경합니다"));
				menu.addMenuClickHandler(19, new MenuClickHandler() {
					
					@Override
					public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
						p.getInventory().setItemInMainHand(getItem(BookDesign.BOOK));
						openSettings(p, p.getInventory().getItemInMainHand());
						return false;
					}
				});
			}
		}
		else if (SlimefunManager.isItemSimiliar(guide, getItem(BookDesign.BOOK), true)) {
			if (p.hasPermission("slimefun.cheat.items")) {
				menu.addItem(19, new CustomItem(new MaterialData(Material.CHEST), "&7가이드 나열 방식: &e책 GUI", "", "&7상자 GUI", "&a책 GUI", "&7치트 GUI", "", "&e 클릭 &8\u21E8 &7나열 방식을 변경합니다"));
				menu.addMenuClickHandler(19, new MenuClickHandler() {
					
					@Override
					public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
						p.getInventory().setItemInMainHand(getItem(BookDesign.CHEAT_SHEET));
						openSettings(p, p.getInventory().getItemInMainHand());
						return false;
					}
				});
			}
			else {
				menu.addItem(19, new CustomItem(new MaterialData(Material.CHEST), "&7가이드 나열 방식: &e책 GUI", "", "&7상자 GUI", "&a책 GUI", "", "&e 클릭 &8\u21E8 &7나열 방식을 변경합니다"));
				menu.addMenuClickHandler(19, new MenuClickHandler() {
					
					@Override
					public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
						p.getInventory().setItemInMainHand(getItem(BookDesign.CHEST));
						openSettings(p, p.getInventory().getItemInMainHand());
						return false;
					}
				});
			}
		}
		else if (SlimefunManager.isItemSimiliar(guide, getItem(BookDesign.CHEAT_SHEET), true)) {
			menu.addItem(19, new CustomItem(new MaterialData(Material.CHEST), "&7가이드 나열 방식: &e치트 GUI", "", "&7상자 GUI", "&7책 GUI", "&a치트 GUI", "", "&e 클릭 &8\u21E8 &7나열 방식을 변경합니다"));
			menu.addMenuClickHandler(19, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
					p.getInventory().setItemInMainHand(getItem(BookDesign.CHEST));
					openSettings(p, p.getInventory().getItemInMainHand());
					return false;
				}
			});
		}
		
		menu.addItem(1, new CustomItem(new MaterialData(Material.BOOK_AND_QUILL), "&a제작자", "", "&7버전: &a" + SlimefunStartup.instance.getDescription().getVersion(), "&7참여자: &e" + contributors.size(), "", "&7\u21E8 이 플러그인 제작에 기여한 사람들을 보려면 클릭하십시오."));
		menu.addMenuClickHandler(1, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
				openCredits(p, guide);
				return false;
			}
		});
		
		try {
			menu.addItem(4, new CustomItem(new MaterialData(Material.REDSTONE_COMPARATOR), "&e소스 코드", "", "&7코드 용량: &6" + IntegerFormat.formatBigNumber(code_bytes), "&7마지막 업데이트: &a" + IntegerFormat.timeDelta(last_update) + " 전", "&7포크: &e" + forks, "", "&7&oSlimefun 4 는 커뮤니티 프로젝트 입니다,", "&7&o소스 코드는 GitHub 에서 사용할 수 있습니다", "&7&o이 플러그인을 계속 사용하기를 원한다면,", "&7&o그것에 대해 기여하는 것을 고려하시기 바랍니다", "", "&7\u21E8 클릭하여 GitHub 보기"));
			menu.addMenuClickHandler(4, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
					p.closeInventory();
					p.sendMessage("");
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&ohttps://github.com/TheBusyBiscuit/Slimefun4"));
					p.sendMessage("");
					return false;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		menu.addItem(7, new CustomItem(new MaterialData(Material.REDSTONE), "&4버그 트래커", "", "&7해결되지 않은 버그: &a" + issues, "", "&7\u21E8 SlimeFun의 버그를 제보해주세요"));
		menu.addMenuClickHandler(7, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
				p.closeInventory();
				p.sendMessage("");
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&ohttps://github.com/TheBusyBiscuit/Slimefun4/issues"));
				p.sendMessage("");
				return false;
			}
		});
		
		menu.open(p);
	}

	@SuppressWarnings("deprecation")
	public static void openCredits(Player p, final ItemStack guide) {
		final ChestMenu menu = new ChestMenu("제작자");
		
		menu.addMenuOpeningHandler(new MenuOpeningHandler() {
			
			@Override
			public void onOpen(Player p) {
				p.playSound(p.getLocation(), Sound.BLOCK_NOTE_HARP, 0.7F, 0.7F);
			}
		});
		
		for (int i = 0; i < 9; i++) {
			if (i != 4) {
				menu.addItem(i, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 7), " "));
				menu.addMenuClickHandler(i, new MenuClickHandler() {
					
					@Override
					public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
						return false;
					}
				});
			}
			else {
				menu.addItem(4, new CustomItem(new MaterialData(Material.EMERALD), "&7\u21E6 설정으로 돌아가기"));
				menu.addMenuClickHandler(4, new MenuClickHandler() {
					
					@Override
					public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
						openSettings(p, guide);
						return false;
					}
				});
			}
		}
		
		int index = 9;
		
		double total = 0;

		for (Contributor contributor: contributors) {
			total += contributor.commits;
		}
		
		for (final Contributor contributor: contributors) {
			ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
			
			ItemMeta meta = skull.getItemMeta();
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&a" + contributor.name));
			
			if (contributor.commits > 0) {
				double percentage = DoubleHandler.fixDouble((contributor.commits * 100.0) / total, 2);
				
				meta.setLore(Arrays.asList("", ChatColor.translateAlternateColorCodes('&', "&7역활: &r" + contributor.job), ChatColor.translateAlternateColorCodes('&', "&7기부: &r" + percentage + "%"), "", ChatColor.translateAlternateColorCodes('&', "&7\u21E8 GitHub 프로필을 보려면 클릭하세요")));
			}
			else {
				meta.setLore(Arrays.asList("", ChatColor.translateAlternateColorCodes('&', "&7역활: &r" + contributor.job)));
			}

			skull.setItemMeta(meta);
			
			menu.addItem(index, skull);
			menu.addMenuClickHandler(index, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player p, int arg1, ItemStack arg2, ClickAction arg3) {
					if (contributor.commits > 0) {
						p.closeInventory();
						p.sendMessage("");
						p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o" + contributor.profile));
						p.sendMessage("");
					}
					return false;
				}
			});
			
			index++;
		}
		
		for (int i = 0; i < 9; i++) {
			menu.addItem(36 + i, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 7), " "));
			menu.addMenuClickHandler(36 + i, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					return false;
				}
			});
		}
		
		menu.open(p);
	}

	public static void openCheatMenu(Player p) {
		openMainMenu(p, false, false, 1);
	}
	
	public static void openGuide(Player p, boolean experimental) {
		if (!SlimefunStartup.getWhitelist().getBoolean(p.getWorld().getName() + ".enabled")) return;
		if (!SlimefunStartup.getWhitelist().getBoolean(p.getWorld().getName() + ".enabled-items.SLIMEFUN_GUIDE")) return;
		if (!history.containsKey(p.getUniqueId())) openMainMenu(p, true, experimental, 1);
		else {
			URID last = getLastEntry(p, false);
			if (URID.decode(last) instanceof Category) openCategory(p, (Category) URID.decode(last), true, 1, experimental);
			else if (URID.decode(last) instanceof SlimefunItem) displayItem(p, ((SlimefunItem) URID.decode(last)).getItem(), false, experimental, 0);
			else if (URID.decode(last) instanceof GuideHandler) ((GuideHandler) URID.decode(last)).run(p, true, experimental);
			else displayItem(p, (ItemStack) URID.decode(last), false, experimental, 0);
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void openMainMenu(final Player p, final boolean survival, final boolean experimental, final int selected_page) {
		clearHistory(p.getUniqueId());
		
		if (experimental) {
			List<TellRawMessage> pages = new ArrayList<TellRawMessage>();
			List<String> texts = new ArrayList<String>();
			List<String> tooltips = new ArrayList<String>();
			List<PlayerRunnable> actions = new ArrayList<PlayerRunnable>();
			
			int tier = 0;
			
			for (final Category category: Category.list()) {
				
				boolean locked = true;
				
				for (SlimefunItem item: category.getItems()) {
					if (Slimefun.isEnabled(p, item, false)) {
						locked = false;
						break;
					}
				}
				
				if (locked) {
					// Dont display that Category...
				}
				else {
					if (tier < category.getTier()) {
						if (survival) {
							for (final GuideHandler handler: Slimefun.getGuideHandlers(tier)) {
								handler.addEntry(texts, tooltips);
								actions.add(new PlayerRunnable(2) {
									
									@Override
									public void run(Player p) {
										handler.run(p, survival, experimental);
									}
								});
							}
						}
						tier = category.getTier();
						if (tier > 1) {
							for (int i = 0; i < 10; i++) {
								if (texts.size() % 10 == 0) break;
								texts.add(" ");
								tooltips.add(null);
								actions.add(null);
							}
						}
						texts.add("&8\u21E8 &6Tier " + tier);
						tooltips.add(null);
						actions.add(null);
					}
					if (category instanceof LockedCategory && !((LockedCategory) category).hasUnlocked(p)) {
						StringBuilder parents = new StringBuilder("&4&l잠겨있음\n\n&7이 카테고리의 잠금을 해제하려면\n&7먼저 이전 카테고리의 모든 항목을\n&7잠금 해제가 필요합니다:\n");
						
						for (Category parent: ((LockedCategory) category).getParents()) {
							parents.append("\n&c" + StringUtils.formatItemName(parent.getItem(), false));
						}
						
						texts.add(shorten("&c" , StringUtils.formatItemName(category.getItem(), false)));
						tooltips.add(parents.toString());
						actions.add(null);
					}
					else if (category instanceof SeasonCategory) {
						if (((SeasonCategory) category).isUnlocked()) {
							texts.add(shorten("&a", StringUtils.formatItemName(category.getItem(), false)));
							tooltips.add("&e다음 카테고리를 해제할려면 클릭하세요\n" + StringUtils.formatItemName(category.getItem(), false));
							actions.add(new PlayerRunnable(1) {
								
								@Override
								public void run(final Player p) {
									Bukkit.getScheduler().scheduleSyncDelayedTask(SlimefunStartup.instance, new Runnable() {
										
										@Override
										public void run() {
											openCategory(p, category, survival, 1, experimental);
										}
									}, 1L);
								}
							});
						}
					}
					else {
						texts.add(shorten("&a", StringUtils.formatItemName(category.getItem(), false)));
						tooltips.add("&e다음 카테고리를 해제할려면 클릭하세요\n" + StringUtils.formatItemName(category.getItem(), false));
						actions.add(new PlayerRunnable(1) {
							
							@Override
							public void run(final Player p) {
								Bukkit.getScheduler().scheduleSyncDelayedTask(SlimefunStartup.instance, new Runnable() {
									
									@Override
									public void run() {
										openCategory(p, category, survival, 1, experimental);
									}
								}, 1L);
							}
						});
					}
				}
			}
			
			if (survival) {
				for (final GuideHandler handler: Slimefun.getGuideHandlers(tier)) {
					handler.addEntry(texts, tooltips);
					actions.add(new PlayerRunnable(2) {
						
						@Override
						public void run(Player p) {
							handler.run(p, survival, experimental);
						}
					});
				}
			}
			
			for (int i = 0; i < texts.size(); i = i + 10) {
				TellRawMessage page = new TellRawMessage();
				page.addText("&b&l- Slimefun 가이드 -\n\n");
				for (int j = i; j < texts.size() && j < i + 10; j++) {
					page.addText(texts.get(j) + "\n");
					if (tooltips.get(j) != null) page.addHoverEvent(HoverAction.SHOW_TEXT, tooltips.get(j));
					if (actions.get(j) != null) page.addClickEvent(actions.get(j));
				}
//				page.addText("\n");
//				if (i > 0) {
//					page.addText("&c<- Prev");
//					page.addHoverEvent(HoverAction.SHOW_TEXT, "&eGo to Page " + (i));
//					page.addClickEvent(me.mrCookieSlime.CSCoreLibPlugin.general.Chat.TellRawMessage.ClickAction.CHANGE_PAGE, String.valueOf(i));
//					page.addText("    ");
//				}
//				if (texts.size() > i * 10) {
//					page.addText("    ");
//					page.addText("&cNext ->");
//					page.addHoverEvent(HoverAction.SHOW_TEXT, "&eGo to Page " + (i + 2));
//					page.addClickEvent(me.mrCookieSlime.CSCoreLibPlugin.general.Chat.TellRawMessage.ClickAction.CHANGE_PAGE, String.valueOf(i + 2));
//				}
				pages.add(page);
			}
			
			new CustomBookOverlay("Slimefun 가이드", "mrCookieSlime", pages.toArray(new TellRawMessage[pages.size()])).open(p);
		}
		else {
			final ChestMenu menu = new ChestMenu("Slimefun 가이드");
			
			menu.addMenuOpeningHandler(new MenuOpeningHandler() {
				
				@Override
				public void onOpen(Player p) {
					p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.7F, 0.7F);
				}
			});
			
			List<Category> categories = Slimefun.current_categories;
			List<GuideHandler> handlers = Slimefun.guide_handlers2;
			
			int index = 9;
			int pages = 1;
			
			for (int i = 0; i < 9; i++) {
				menu.addItem(i, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 7), " "));
				menu.addMenuClickHandler(i, new MenuClickHandler() {
					
					@Override
					public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
						return false;
					}
				});
			}
			
			for (int i = 45; i < 54; i++) {
				menu.addItem(i, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 7), " "));
				menu.addMenuClickHandler(i, new MenuClickHandler() {
					
					@Override
					public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
						return false;
					}
				});
			}
			
			int target = (category_size * (selected_page - 1)) - 1;
			
			while (target < (categories.size() + handlers.size() - 1)) {
				if (index >= category_size + 9) {
					pages++;
					break;
				}
				
				target++;
				
				if (target >= categories.size()) {
					if (!survival) break;
					index = handlers.get(target - categories.size()).next(p, index, menu);
				}
				else {
					final Category category = categories.get(target);
					
					boolean locked = true;
					
					for (SlimefunItem item: category.getItems()) {
						if (Slimefun.isEnabled(p, item, false)) {
							locked = false;
							break;
						}
					}
					
					if (locked) {
						// Dont display that Category...
					}
					else if (!(category instanceof LockedCategory)) {
						if (!(category instanceof SeasonCategory)) {
							menu.addItem(index, category.getItem());
							menu.addMenuClickHandler(index, new MenuClickHandler() {
								
								@Override
								public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
									openCategory(p, category, survival, 1, experimental);
									return false;
								}
							});
							index++;
						}
						else {
							if (((SeasonCategory) category).isUnlocked()) {
								menu.addItem(index, category.getItem());
								menu.addMenuClickHandler(index, new MenuClickHandler() {
									
									@Override
									public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
										openCategory(p, category, survival, 1, experimental);
										return false;
									}
								});
								index++;
							}
						}
					}
					else if (((LockedCategory) category).hasUnlocked(p)) {
						menu.addItem(index, category.getItem());
						menu.addMenuClickHandler(index, new MenuClickHandler() {
							
							@Override
							public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
								openCategory(p, category, survival, 1, experimental);
								return false;
							}
						});
						index++;
					}
					else {
						List<String> parents = new ArrayList<String>();
						parents.add("");
						parents.add("&r모든 항목의 잠금 해제가 필요합니다");
						parents.add("&r다음과 같은 카테고리에서 필요합니다");
						parents.add("");
						for (Category parent: ((LockedCategory) category).getParents()) {
							parents.add(parent.getItem().getItemMeta().getDisplayName());
						}
						menu.addItem(index, new CustomItem(Material.BARRIER, "&4LOCKED &7- &r" + category.getItem().getItemMeta().getDisplayName(), 0, parents.toArray(new String[parents.size()])));
						menu.addMenuClickHandler(index, new MenuClickHandler() {
							
							@Override
							public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
								return false;
							}
						});
						index++;
					}
				}
			}

			final int finalPages = pages;
			
			menu.addItem(46, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 5), "&r\u21E6 이전 페이지", "", "&7(" + selected_page + " / " + pages + ")"));
			menu.addMenuClickHandler(46, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					int next = selected_page - 1;
					if (next < 1) next = finalPages;
					if (next != selected_page) openMainMenu(p, survival, experimental, next);
					return false;
				}
			});
			
			menu.addItem(52, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 5), "&r다음 페이지 \u21E8", "", "&7(" + selected_page + " / " + pages + ")"));
			menu.addMenuClickHandler(52, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					int next = selected_page + 1;
					if (next > finalPages) next = 1;
					if (next != selected_page) openMainMenu(p, survival, experimental, next);
					return false;
				}
			});
			
			menu.open(p);
		}
	}

	public static String shorten(String string, String string2) {
		if (ChatColor.stripColor(string + string2).length() > 19) return (string + ChatColor.stripColor(string2)).substring(0, 18) + "...";
		else return (string + ChatColor.stripColor(string2));
	}

	@SuppressWarnings("deprecation")
	public static void openCategory(final Player p, final Category category, final boolean survival, final int selected_page, final boolean experimental) {
		if (category == null) return;

		if (experimental && category.getItems().size() < 250) {
			List<TellRawMessage> pages = new ArrayList<TellRawMessage>();
			List<String> texts = new ArrayList<String>();
			List<String> tooltips = new ArrayList<String>();
			List<PlayerRunnable> actions = new ArrayList<PlayerRunnable>();
			
			for (final SlimefunItem item: category.getItems()) {
				if (Slimefun.hasPermission(p, item, false)) {
					if (Slimefun.isEnabled(p, item, false)) {
						if (survival && !Slimefun.hasUnlocked(p, item, false) && item.getResearch() != null) {
						    final Research research = item.getResearch();
						    
							texts.add(shorten("&7", StringUtils.formatItemName(item.getItem(), false)));
							tooltips.add(StringUtils.formatItemName(item.getItem(), false) + "\n&c&l잠겨짐\n\n&7비용: " + (p.getLevel() >= research.getCost() ? "&b": "&4") + research.getCost() + " Levels\n\n&a> 클릭하여 잠금 해제");
							actions.add(new PlayerRunnable(2) {
								
								@Override
								public void run(final Player p) {
									if (!Research.isResearching(p)) {
										if (research.canUnlock(p)) {
											if (research.hasUnlocked(p))
												openCategory(p, category, true, selected_page, experimental);
											else {
												if (!(p.getGameMode() == GameMode.CREATIVE && Research.creative_research)) {
													p.setLevel(p.getLevel() - research.getCost());
												}

												if (p.getGameMode() == GameMode.CREATIVE) {
													research.unlock(p, true);
													Bukkit.getScheduler().scheduleSyncDelayedTask(SlimefunStartup.instance, new Runnable() {

														@Override
														public void run() {
															openCategory(p, category, survival, selected_page, experimental);
														}
													}, 1L);
												} else {
													research.unlock(p, false);
													Bukkit.getScheduler().scheduleSyncDelayedTask(SlimefunStartup.instance, new Runnable() {

														@Override
														public void run() {
															openCategory(p, category, survival, selected_page, experimental);
														}
													}, 103L);
												}
											}
										} else Messages.local.sendTranslation(p, "messages.not-enough-xp", true);
									}
								}
							});
						}
						else {
							texts.add(shorten("&a", StringUtils.formatItemName(item.getItem(), false)));
							
							StringBuilder tooltip = new StringBuilder();
							
							tooltip.append(StringUtils.formatItemName(item.getItem(), false));
							
							if (item.getItem().hasItemMeta() && item.getItem().getItemMeta().hasLore()) {
								for (String line: item.getItem().getItemMeta().getLore()) {
									tooltip.append("\n" + line);
								}
							}
							
							tooltip.append("\n\n&e&o클릭하여 더 자세한 정보를 볼 수 있습니다");
							
							tooltips.add(tooltip.toString());
							actions.add(new PlayerRunnable(2) {
								
								@Override
								public void run(Player p) {
									displayItem(p, item.getItem(), true, experimental, 0);
								}
							});
						}
					}
				}
				else {
					texts.add(shorten("&4", StringUtils.formatItemName(item.getItem(), false)));
					tooltips.add("&c권한이 없습니다!");
					actions.add(null);
				}
			}
			
			for (int i = 0; i < texts.size(); i = i + 10) {
				TellRawMessage page = new TellRawMessage();
				page.addText("&b&l- Slimefun 가이드 -\n\n");
				for (int j = i; j < texts.size() && j < i + 10; j++) {
					page.addText(texts.get(j) + "\n");
					if (tooltips.get(j) != null) page.addHoverEvent(HoverAction.SHOW_TEXT, tooltips.get(j));
					if (actions.get(j) != null) page.addClickEvent(actions.get(j));
				}
				page.addText("\n");
				page.addText("&6\u21E6 &lBack");
				page.addHoverEvent(HoverAction.SHOW_TEXT, "&e카테고리 개요로 돌아가려면 클릭하세요");
				page.addClickEvent(new PlayerRunnable(2) {
					
					@Override
					public void run(final Player p) {
						Bukkit.getScheduler().scheduleSyncDelayedTask(SlimefunStartup.instance, new Runnable() {
							
							@Override
							public void run() {
								openMainMenu(p, survival, true, 1);
							}
						}, 1L);
					}
				});
				pages.add(page);
			}
			
			new CustomBookOverlay("Slimefun 가이드", "mrCookieSlime", pages.toArray(new TellRawMessage[pages.size()])).open(p);
		}
		else {
			final ChestMenu menu = new ChestMenu("Slimefun 가이드");
			
			menu.addMenuOpeningHandler(new MenuOpeningHandler() {
				
				@Override
				public void onOpen(Player p) {
					p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.7F, 0.7F);
				}
			});
			
			int index = 9;
			final int pages = category.getItems().size() / category_size + 1;
			for (int i = 0; i < 4; i++) {
				menu.addItem(i, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 7), " "));
				menu.addMenuClickHandler(i, new MenuClickHandler() {
					
					@Override
					public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
						return false;
					}
				});
			}
			
			menu.addItem(4, new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&7\u21E6 뒤로가기"));
			menu.addMenuClickHandler(4, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					openMainMenu(p, survival, experimental, 1);
					return false;
				}
			});
			
			for (int i = 5; i < 9; i++) {
				menu.addItem(i, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 7), " "));
				menu.addMenuClickHandler(i, new MenuClickHandler() {
					
					@Override
					public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
						return false;
					}
				});
			}
			
			for (int i = 45; i < 54; i++) {
				menu.addItem(i, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 7), " "));
				menu.addMenuClickHandler(i, new MenuClickHandler() {
					
					@Override
					public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
						return false;
					}
				});
			}
			
			menu.addItem(46, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 5), "&r\u21E6 이전 페이지", "", "&7(" + selected_page + " / " + pages + ")"));
			menu.addMenuClickHandler(46, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					int next = selected_page - 1;
					if (next < 1) next = pages;
					if (next != selected_page) openCategory(p, category, survival, next, experimental);
					return false;
				}
			});
			
			menu.addItem(52, new CustomItem(new MaterialData(Material.STAINED_GLASS_PANE, (byte) 5), "&r다음 페이지 \u21E8", "", "&7(" + selected_page + " / " + pages + ")"));
			menu.addMenuClickHandler(52, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
					int next = selected_page + 1;
					if (next > pages) next = 1;
					if (next != selected_page) openCategory(p, category, survival, next, experimental);
					return false;
				}
			});
			
			int category_index = category_size * (selected_page - 1);
			for (int i = 0; i < category_size; i++) {
				int target = category_index + i;
				if (target >= category.getItems().size()) break;
				final SlimefunItem sfitem = category.getItems().get(target);
				if (Slimefun.isEnabled(p, sfitem, false)) {
					if (survival && !Slimefun.hasUnlocked(p, sfitem.getItem(), false) && sfitem.getResearch() != null) {
						if (Slimefun.hasPermission(p, sfitem, false)) {
						    final Research research = sfitem.getResearch();
							menu.addItem(index, new CustomItem(Material.BARRIER, "&r" + StringUtils.formatItemName(sfitem.getItem(), false), 0, new String[] {"&4&l잠겨짐", "", "&a> 클릭하여 잠금 해제", "", "&7비용: &b" + research.getCost() + " 레벨"}));
							menu.addMenuClickHandler(index, new MenuClickHandler() {
								
								@Override
								public boolean onClick(final Player p, int slot, ItemStack item, ClickAction action) {
									if (!Research.isResearching(p)) {
										if (research.canUnlock(p)) {
											if (research.hasUnlocked(p))
												openCategory(p, category, true, selected_page, experimental);
											else {
												if (!(p.getGameMode() == GameMode.CREATIVE && Research.creative_research)) {
													p.setLevel(p.getLevel() - research.getCost());
												}

												if (p.getGameMode() == GameMode.CREATIVE) {
													research.unlock(p, Research.creative_research);
													openCategory(p, category, survival, selected_page, experimental);
												} else {
													research.unlock(p, false);
													Bukkit.getScheduler().scheduleSyncDelayedTask(SlimefunStartup.instance, new Runnable() {

														@Override
														public void run() {
															openCategory(p, category, survival, selected_page, experimental);
														}
													}, 103L);
												}
											}
										} else Messages.local.sendTranslation(p, "messages.not-enough-xp", true);
									}
									return false;
								}
							});
							index++;
						}
						else {
							menu.addItem(index, new CustomItem(Material.BARRIER, StringUtils.formatItemName(sfitem.getItem(), false), 0, new String[] {"", "&r권한이 없습니다", "&r이 항목에 액세스 할 수 없습니다"}));
							menu.addMenuClickHandler(index, new MenuClickHandler() {
								
								@Override
								public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
									return false;
								}
							});
							index++;
						}
					}
					else {
						menu.addItem(index, sfitem.getItem());
						menu.addMenuClickHandler(index, new MenuClickHandler() {
							
							@Override
							public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
								if (survival) displayItem(p, item, true, experimental, 0);
								else p.getInventory().addItem(item);
								return false;
							}
						});
						index++;
					}
				}
			}
			
			menu.open(p);
		}		

		if (survival) {
			addToHistory(p, category.getURID());
		}
	}

	public static void addToHistory(Player p, URID urid) {
		List<URID> list = new ArrayList<URID>();
		if (history.containsKey(p.getUniqueId())) list = history.get(p.getUniqueId());
		list.add(urid);
		history.put(p.getUniqueId(), list);
	}
	
	private static URID getLastEntry(Player p, boolean remove) {
		List<URID> list = new ArrayList<URID>();
		if (history.containsKey(p.getUniqueId())) list = history.get(p.getUniqueId());
		if (remove && list.size() >= 1) {
			URID urid = list.get(list.size() - 1);
			urid.markDirty();
			list.remove(urid);
		}
		if (list.isEmpty()) history.remove(p.getUniqueId());
		else history.put(p.getUniqueId(), list);
		return list.isEmpty() ? null: list.get(list.size() - 1);
	}
	
	@SuppressWarnings("deprecation")
	public static void displayItem(Player p, final ItemStack item, boolean addToHistory, final boolean experimental, final int page) {
		if (item == null || item.getType() == Material.AIR) return;

		final SlimefunItem sfItem = SlimefunItem.getByItem(item);
		if (sfItem == null) {
			if (!all_recipes) return;
		}
		
		ItemStack[] recipe = new ItemStack[9];
		ItemStack recipeType = null;
		ItemStack recipeOutput = item;
		
		ChestMenu menu = new ChestMenu("Slimefun 가이드");
		
		menu.addMenuOpeningHandler(new MenuOpeningHandler() {
			
			@Override
			public void onOpen(Player p) {
				p.playSound(p.getLocation(), Sound.ENTITY_BAT_TAKEOFF, 0.7F, 0.7F);
			}
		});

		if (sfItem != null) {
			recipe = sfItem.getRecipe();
			recipeType = sfItem.getRecipeType().toItem();
			recipeOutput = sfItem.getCustomOutput() != null ? sfItem.getCustomOutput(): sfItem.getItem();
		}
		else {
			List<Recipe> recipes = new ArrayList<Recipe>();
			Iterator<Recipe> iterator = Bukkit.recipeIterator();
			while (iterator.hasNext()) {
				Recipe r = iterator.next();
				if (SlimefunManager.isItemSimiliar(new CustomItem(r.getResult(), 1), item, true) && r.getResult().getData().getData() == item.getData().getData()) recipes.add(r);
			}
			
			if (recipes.isEmpty()) return;
			 Recipe r = recipes.get(page);
			 
			 if (recipes.size() > page + 1) {
				 menu.addItem(1, new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&7다음 \u21E8", "", "&e&l! &r이 아이템에 대한 여러 가지 조합법이 있습니다"));
					menu.addMenuClickHandler(1, new MenuClickHandler() {
						
						@Override
						public boolean onClick(Player p, int slot, ItemStack stack, ClickAction action) {
							displayItem(p, item, false, experimental, page + 1);
							return false;
						}
					});
			 }
			 
			 if (r instanceof ShapedRecipe) {
					String[] shape = ((ShapedRecipe) r).getShape();
					for (int i = 0; i < shape.length; i++) {
			            for (int j = 0; j < shape[i].length(); j++) {
			            	ItemStack ingredient = ((ShapedRecipe) r).getIngredientMap().get(shape[i].charAt(j));
							if (ingredient != null) {
								MaterialData data = ingredient.getData();
								if (ingredient.getData().getData() < 0) data.setData((byte) 0);
								ingredient = data.toItemStack(ingredient.getAmount());
							}
							recipe[i * 3 + j] = ingredient;
			            }
			        }
					recipeType = RecipeType.SHAPED_RECIPE.toItem();
					recipeOutput = r.getResult();
				}
				else if (r instanceof ShapelessRecipe) {
			        List<ItemStack> ingredients = ((ShapelessRecipe) r).getIngredientList();
					for (int i = 0; i < ingredients.size(); i++) {
						ItemStack ingredient = ingredients.get(i);
						if (ingredient != null) {
							MaterialData data = ingredient.getData();
							if (ingredient.getData().getData() < 0) data.setData((byte) 0);
							ingredient = data.toItemStack(ingredient.getAmount());
						}
			            recipe[i] = ingredient;
			        }
					recipeType = RecipeType.SHAPELESS_RECIPE.toItem();
					recipeOutput = r.getResult();
				}
				else if (r instanceof FurnaceRecipe) {
					ItemStack ingredient = ((FurnaceRecipe) r).getInput();
					if (ingredient != null) {
						MaterialData data = ingredient.getData();
						if (ingredient.getData().getData() < 0) data.setData((byte) 0);
						ingredient = data.toItemStack(ingredient.getAmount());
					}
					recipe[4] = ingredient;
					
					recipeType = RecipeType.FURNACE.toItem();
					recipeOutput = r.getResult();
				}
		}
		

		
		if (addToHistory) addToHistory(p, sfItem != null ? sfItem.getURID(): URID.nextURID(item, true));
		
		if (history.containsKey(p.getUniqueId()) && history.get(p.getUniqueId()).size() > 1) {
			menu.addItem(0, new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&7\u21E6 이전", "", "&r왼 클릭: &7이전 페이지로 돌아가기", "&r쉬프트 + 왼 클릭: &7메인 메뉴로 돌아가기"));
			menu.addMenuClickHandler(0, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
					if (action.isShiftClicked()) openMainMenu(p, true, experimental, 1);
					else {
						URID last = getLastEntry(p, true);
						if (URID.decode(last) instanceof Category) openCategory(p, (Category) URID.decode(last), true, 1, experimental);
						else if (URID.decode(last) instanceof SlimefunItem) displayItem(p, ((SlimefunItem) URID.decode(last)).getItem(), false, experimental, 0);
						else if (URID.decode(last) instanceof GuideHandler) ((GuideHandler) URID.decode(last)).run(p, true, experimental);
						else displayItem(p, (ItemStack) URID.decode(last), false, experimental, 0);
					}
					return false;
				}
			});
		}
		else {
			menu.addItem(0, new CustomItem(new MaterialData(Material.ENCHANTED_BOOK), "&7\u21E6 이전", "", "&r왼 클릭: &7메인 메뉴로 돌아가기"));
			menu.addMenuClickHandler(0, new MenuClickHandler() {
				
				@Override
				public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
					openMainMenu(p, true, experimental, 1);
					return false;
				}
			});
		}
		
		menu.addItem(3, Slimefun.hasUnlocked(p, recipe[0], false) ? recipe[0]: new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[0], false), 0, new String[] {"&4&l잠겨짐", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[0]), false) ? "&r다른 곳에서의 잠금 해제가 필요합니다" : "&r권한이 없습니다"}));
		menu.addMenuClickHandler(3, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				displayItem(p, item, true, experimental, 0);
				return false;
			}
		});
		
		menu.addItem(4, Slimefun.hasUnlocked(p, recipe[1], false) ? recipe[1]: new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[1], false), 0, new String[] {"&4&l잠겨짐", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[1]), false) ? "&r다른 곳에서의 잠금 해제가 필요합니다" : "&r권한이 없습니다"}));
		menu.addMenuClickHandler(4, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				displayItem(p, item, true, experimental, 0);
				return false;
			}
		});
		
		menu.addItem(5, Slimefun.hasUnlocked(p, recipe[2], false) ? recipe[2]: new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[2], false), 0, new String[] {"&4&l잠겨짐", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[2]), false) ? "&r다른 곳에서의 잠금 해제가 필요합니다" : "&r권한이 없습니다"}));
		menu.addMenuClickHandler(5, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				displayItem(p, item, true, experimental, 0);
				return false;
			}
		});
		
		if (sfItem != null) {
			if (Slimefun.getItemConfig().contains(sfItem.getName() + ".wiki")) {
				try {
					menu.addItem(8, new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzY2OTJmOTljYzZkNzgyNDIzMDQxMTA1NTM1ODk0ODQyOThiMmU0YTAyMzNiNzY3NTNmODg4ZTIwN2VmNSJ9fX0="), "&r위키를 볼 수 있습니다 &7(Slimefun 위키)", "", "&7\u21E8 클릭하여 열기"));
					menu.addMenuClickHandler(8, new MenuClickHandler() {
						
						@Override
						public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
							p.closeInventory();
							p.sendMessage("");
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o" + Slimefun.getItemConfig().getString(sfItem.getName() + ".wiki")));
							p.sendMessage("");
							return false;
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (Slimefun.getItemConfig().contains(sfItem.getName() + ".youtube")) {
				try {
					menu.addItem(7, new CustomItem(CustomSkull.getItem("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjQzNTNmZDBmODYzMTQzNTM4NzY1ODYwNzViOWJkZjBjNDg0YWFiMDMzMWI4NzJkZjExYmQ1NjRmY2IwMjllZCJ9fX0="), "&r데모 영상 &7(Youtube)", "", "&7\u21E8 클릭하여 보기"));
					menu.addMenuClickHandler(7, new MenuClickHandler() {
						
						@Override
						public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
							p.closeInventory();
							p.sendMessage("");
							p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7&o" + Slimefun.getItemConfig().getString(sfItem.getName() + ".youtube")));
							p.sendMessage("");
							return false;
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		menu.addItem(10, recipeType);
		menu.addMenuClickHandler(10, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				return false;
			}
		});
		
		menu.addItem(12, Slimefun.hasUnlocked(p, recipe[3], false) ? recipe[3]: new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[3], false), 0, new String[] {"&4&l잠겨짐", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[3]), false) ? "&r다른 곳에서의 잠금 해제가 필요합니다" : "&r권한이 없습니다"}));
		menu.addMenuClickHandler(12, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				displayItem(p, item, true, experimental, 0);
				return false;
			}
		});
		
		menu.addItem(13, Slimefun.hasUnlocked(p, recipe[4], false) ? recipe[4]: new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[4], false), 0, new String[] {"&4&l잠겨짐", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[4]), false) ? "&r&r다른 곳에서의 잠금 해제가 필요합니다" : "&r권한이 없습니다"}));
		menu.addMenuClickHandler(13, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				displayItem(p, item, true, experimental, 0);
				return false;
			}
		});
		
		menu.addItem(14, Slimefun.hasUnlocked(p, recipe[5], false) ? recipe[5]: new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[5], false), 0, new String[] {"&4&l잠겨짐", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[5]), false) ? "&r&r다른 곳에서의 잠금 해제가 필요합니다" : "&r권한이 없습니다"}));
		menu.addMenuClickHandler(14, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				displayItem(p, item, true, experimental, 0);
				return false;
			}
		});
		
		menu.addItem(16, recipeOutput);
		menu.addMenuClickHandler(16, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				return false;
			}
		});
		
		menu.addItem(21, Slimefun.hasUnlocked(p, recipe[6], false) ? recipe[6]: new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[6], false), 0, new String[] {"&4&l잠겨짐", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[6]), false) ? "&r&r다른 곳에서의 잠금 해제가 필요합니다" : "&r권한이 없습니다"}));
		menu.addMenuClickHandler(21, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				displayItem(p, item, true, experimental, 0);
				return false;
			}
		});
		
		menu.addItem(22, Slimefun.hasUnlocked(p, recipe[7], false) ? recipe[7]: new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[7], false), 0, new String[] {"&4&l잠겨짐", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[7]), false) ? "&r다른 곳에서의 잠금 해제가 필요합니다" : "&r권한이 없습니다"}));
		menu.addMenuClickHandler(22, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				displayItem(p, item, true, experimental, 0);
				return false;
			}
		});
		
		menu.addItem(23, Slimefun.hasUnlocked(p, recipe[8], false) ? recipe[8]: new CustomItem(Material.BARRIER, StringUtils.formatItemName(recipe[8], false), 0, new String[] {"&4&l잠겨짐", "", Slimefun.hasPermission(p, SlimefunItem.getByItem(recipe[8]), false) ? "&r다른 곳에서의 잠금 해제가 필요합니다" : "&r권한이 없습니다"}));
		menu.addMenuClickHandler(23, new MenuClickHandler() {
			
			@Override
			public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
				displayItem(p, item, true, experimental, 0);
				return false;
			}
		});
		
		if (sfItem != null) {
			
			if ((sfItem instanceof SlimefunMachine && ((SlimefunMachine) sfItem).getDisplayRecipes().size() > 0) || (sfItem instanceof SlimefunGadget && ((SlimefunGadget) sfItem).getRecipes().size() > 0)) {
				for (int i = 27; i < 36; i++) {
					menu.addItem(i, new CustomItem(Material.STAINED_GLASS_PANE, SlimefunItem.getByItem(item) instanceof SlimefunMachine ? "&7\u21E9 이 기계로 만든 조합법 \u21E9": " ", 7));
					menu.addMenuClickHandler(i, new MenuClickHandler() {
						
						@Override
						public boolean onClick(Player arg0, int arg1, ItemStack arg2, ClickAction arg3) {
							return false;
						}
					});
				}
				
				List<ItemStack> recipes = SlimefunItem.getByItem(item) instanceof SlimefunMachine ? ((SlimefunMachine) SlimefunItem.getByItem(item)).getDisplayRecipes(): ((SlimefunGadget) SlimefunItem.getByItem(item)).getDisplayRecipes();
				int recipe_size = recipes.size();
				if (recipe_size > 18) recipe_size = 18;
				int inputs = -1, outputs = -1;
				
				for (int i = 0; i < recipe_size; i++) {
					int slot = 36;
					if (i % 2 == 1) {
						slot = slot + 9;
						outputs++;
					}
					else inputs++;
					
					int addition = (i % 2 == 0 ? inputs: outputs);
					
					menu.addItem(slot + addition, recipes.get(i));
					menu.addMenuClickHandler(slot + addition, new MenuClickHandler() {
						
						@Override
						public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
						    displayItem(p, item, true, experimental, 0);
							return false;
						}
					});
				}
			}
			else if (sfItem instanceof AGenerator) {
				int slot = 27;
				for (MachineFuel fuel: ((AGenerator) sfItem).getFuelTypes()) {
					if (slot > 54) break;
					ItemStack fItem = fuel.getInput().clone();
					ItemMeta im = fItem.getItemMeta();
					List<String> lore = new ArrayList<String>();
					lore.add(ChatColor.translateAlternateColorCodes('&', "&8\u21E8 &7Lasts " + getTimeLeft(fuel.getTicks() / 2)));
					lore.add(ChatColor.translateAlternateColorCodes('&', "&8\u21E8 &e\u26A1 &7" + (((AGenerator) sfItem).getEnergyProduction() * 2) + " J/s"));
					lore.add(ChatColor.translateAlternateColorCodes('&', "&8\u21E8 &e\u26A1 &7" + DoubleHandler.getFancyDouble(fuel.getTicks() * ((AGenerator) sfItem).getEnergyProduction()) + " J in total"));
					im.setLore(lore);
					fItem.setItemMeta(im);
					menu.addItem(slot, fItem);
					menu.addMenuClickHandler(slot, new MenuClickHandler() {
						
						@Override
						public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
							return false;
						}
					});
					slot++;
				}
			}
			else if (sfItem instanceof AReactor) {
				int slot = 27;
				for (MachineFuel fuel: ((AReactor) sfItem).getFuelTypes()) {
					if (slot > 54) break;
					ItemStack fItem = fuel.getInput().clone();
					ItemMeta im = fItem.getItemMeta();
					List<String> lore = new ArrayList<String>();
					lore.add(ChatColor.translateAlternateColorCodes('&', "&8\u21E8 &7Lasts " + getTimeLeft(fuel.getTicks() / 2)));
					lore.add(ChatColor.translateAlternateColorCodes('&', "&8\u21E8 &e\u26A1 &7" + (((AReactor) sfItem).getEnergyProduction() * 2) + " J/s"));
					lore.add(ChatColor.translateAlternateColorCodes('&', "&8\u21E8 &e\u26A1 &7" + DoubleHandler.getFancyDouble(fuel.getTicks() * ((AReactor) sfItem).getEnergyProduction()) + " J in total"));
					im.setLore(lore);
					fItem.setItemMeta(im);
					menu.addItem(slot, fItem);
					menu.addMenuClickHandler(slot, new MenuClickHandler() {
						
						@Override
						public boolean onClick(Player p, int slot, ItemStack item, ClickAction action) {
							return false;
						}
					});
					slot++;
				}
			}
		}
		
		menu.build().open(p);
	}
	
	public static void clearHistory(UUID uuid) {
		if (!history.containsKey(uuid)) return;
		
		for (URID urid: history.get(uuid)) {
			urid.markDirty();
		}
		history.remove(uuid);
	}
	
	private static String getTimeLeft(int l) {
		String timeleft = "";
        final int minutes = (int) (l / 60L);
        if (minutes > 0) {
            timeleft = String.valueOf(timeleft) + minutes + "분 ";
        }
        l -= minutes * 60;
        final int seconds = (int)l;
        timeleft = String.valueOf(timeleft) + seconds + "초";
        return "&7" + timeleft;
	}

}
